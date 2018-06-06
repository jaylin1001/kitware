package com.kitware.guntae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.guntae.vo.Gunte;
import com.kitware.guntae.vo.Yeoncha;
import com.kitware.sql.MyConnection;


public class GTDAOOracle implements GTDAO {

	//근태 모든것 셀렉트
	@Override
	public List<Gunte> gselectAll(String emp_num) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Gunte> gslist = new ArrayList<>();

		String gunteselectSQL = "select emp_num, to_char(in_day,'yyyy-MM-dd') in_day, to_char(in_time,'hh24:mi:ss') in_time, to_char(out_time,'hh24:mi:ss') out_time, doc_kind from gunte where emp_num = ?";
		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(gunteselectSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			//gslist.add( new Gunte(rs.getString("emp_num"), rs.getString("in_day"), rs.getString("in_time"), rs.getString("out_time"), rs.getInt("doc_kind")));
				Gunte gunte = new Gunte();
				gunte.setEmp_num(rs.getString("emp_num"));
				gunte.setIn_day(rs.getString("in_day"));
				gunte.setIn_time(rs.getString("in_time"));
				gunte.setOut_time(rs.getString("out_time"));
				gunte.setDoc_kind(rs.getInt("doc_kind"));
				gslist.add(gunte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return gslist;
	}
	
	//근태 출석 인설트
	@Override
	public void ininsert(String emp_num) throws Exception {	
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;

		String ininsertSQL = "INSERT INTO gunte (emp_num, in_day, in_time, out_time, doc_kind)\r\n" + 
				"VALUES(?, to_char(sysdate,'yyMMdd'), sysdate, null, null)";
		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(ininsertSQL);
			pstmt.setString(1, emp_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	//근태 퇴근 업데이트
	@Override
	public void outupdate(String emp_num) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;

		String yuseUpdateSQL = "update gunte\r\n" + 
				"set out_time = sysdate\r\n" + 
				"where emp_num = ?\r\n" + 
				"and in_day = to_char(sysdate,'yyMMdd')";
		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(yuseUpdateSQL);
			pstmt.setString(1, emp_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}
	
	//근태 상태 셀렉트
	@Override
	public List<DocGiganVO> dockindselectAll(String emp_num, String doc_kind) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DocGiganVO> dockindlist = new ArrayList<>();
	
		String dockindselectAllSQL = "select dg.start_date, dg.end_date\r\n" + 
				"from doc_gigan dg, document d\r\n" + 
				"where d.doc_num = dg.doc_num\r\n" + 
				"and d.emp_num = ?\r\n" + 
				"and d.doc_kind = ?";
				/*"and d.doc_state = 1";*/
				
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(dockindselectAllSQL);
			pstmt.setString(1, emp_num);
			pstmt.setString(2, doc_kind);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DocGiganVO docgiganvo = new DocGiganVO();
				docgiganvo.setStart_date(rs.getString("start_date"));
				docgiganvo.setEnd_date(rs.getString("end_date"));	
				dockindlist.add(docgiganvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return dockindlist;
	}
	
	// 연차의 모든것 select
	@Override
	public Yeoncha selectAll(String emp_num, String years) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Yeoncha yc = null;

		String yeonchaselectSQL = "select * from yeoncha where emp_num = ? and years = ?";

		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(yeonchaselectSQL);
			// int num = Integer.parseInt(emp_num);
			pstmt.setString(1, emp_num);
			pstmt.setString(2, years);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				yc = new Yeoncha(rs.getInt("years"), rs.getInt("emp_num"), rs.getInt("all_yeoncha"),
						rs.getInt("use_yeoncha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return yc;
	}

	// 연차 총사용연차 업데이트
	@Override
	public void useupdate(String emp_num, String years) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;

		String yuseUpdateSQL = "update yeoncha\r\n"
				+ "set use_yeoncha = (select nvl(sum(to_date(end_date) - to_date(start_date) + 1), 0)\r\n"
				+ "                    from yeoncha_gigan\r\n" + "                    where emp_num = ?\r\n"
				+ "                    and years=?)\r\n" + "where emp_num = ?\r\n" + "and years=?";

		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(yuseUpdateSQL);
			pstmt.setString(1, emp_num);
			pstmt.setString(2, years);
			pstmt.setString(3, emp_num);
			pstmt.setString(4, years);
			pstmt.executeUpdate();
			// System.out.println("11");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	// 연차기간 모든것 셀렉트
	@Override
	public List<List<String>> giganselectAll(String emp_num, String years) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String giganselectSQL = "select start_date, end_date, (to_date(end_date)-to_date(start_date))+1 days from yeoncha_gigan where emp_num = ? and years = ?";

		List<List<String>> yeonchagiganlist = new ArrayList<>();

		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(giganselectSQL);
			pstmt.setString(1, emp_num);
			pstmt.setString(2, years);
			rs = pstmt.executeQuery();
			// System.out.println("11");
			while (rs.next()) {
				List<String> list = new ArrayList<>();
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				yeonchagiganlist.add(list);
				// System.out.println("22");
			}
			// System.out.println(yeonchalist.size()+"1233");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return yeonchagiganlist;
	}

	// 연차 기간 doc_gigan에서 인설트
	@Override
	public void giganinsert(String emp_num) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;

		String giganInsertSQL = "insert into yeoncha_gigan (emp_num, years, start_date, end_date)\r\n"
				+ "select emp_num, substr(start_date,0,4), start_date, end_date\r\n"
				+ "from (select d.emp_num,  dg.start_date, dg.end_date\r\n" + "    from DOC_GIGAN dg, document d\r\n"
				+ "    where d.doc_num = dg.doc_num\r\n" + "    and doc_kind=30)\r\n" + "where emp_num = ?";

		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(giganInsertSQL);
			pstmt.setString(1, emp_num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}
	
	public static void main(String[] args) {
		GTDAOOracle test = new GTDAOOracle();
		try {
			List<Gunte> list = test.gselectAll("1");
			System.out.println(list);
			
			List<DocGiganVO> dockindlist = test.dockindselectAll("1", "40");
			System.out.println(dockindlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
