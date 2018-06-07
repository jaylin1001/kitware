package com.kitware.guntae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.sql.MyConnection;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.guntae.vo.Gunte;
import com.kitware.guntae.vo.Yeoncha;

public class GTDAOOracle implements GTDAO {

	// 근태 모든것 셀렉트
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

	// 근태 출석 인설트
	@Override
	public void ininsert(String emp_num) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;

		String ininsertSQL = "INSERT INTO gunte (emp_num, in_day, in_time, out_time, doc_kind)\r\n"
				+ "VALUES(?, to_char(sysdate,'yyMMdd'), sysdate, null, null)";
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

	// 근태 퇴근 업데이트
	@Override
	public void outupdate(String emp_num) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;

		String yuseUpdateSQL = "update gunte\r\n" + "set out_time = sysdate\r\n" + "where emp_num = ?\r\n"
				+ "and in_day = to_char(sysdate,'yyMMdd')";
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

	// 근태 상태 셀렉트
	@Override
	public List<DocGiganVO> dockindselectAll(String emp_num, String doc_kind) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DocGiganVO> dockindlist = new ArrayList<>();

		String dockindselectAllSQL = "select dg.start_date, dg.end_date\r\n" + "from doc_gigan dg, document d\r\n"
				+ "where d.doc_num = dg.doc_num\r\n" + "and d.emp_num = ?\r\n" + "and d.doc_kind = ?\r\n"
				+ "and d.doc_state = 2";

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

	// 근태 상태 카운트값
	@Override
	public int selectbyungamonth(String years, String months, int doc_kind, String emp_num) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		// 출장:40, 연차:50, 병가:60, 조퇴:80
		String dockindcountselectSQL = "select sum(b.doccount) aa\r\n" + "from(\r\n" + "select a.*\r\n"
				+ "from(select  substr(to_char(dg.start_date), 1, 4) years, substr(to_char(dg.start_date), 6, 2) months, (to_date(dg.end_date) - to_date(dg.start_date) + 1) doccount\r\n"
				+ "from doc_gigan dg, document d\r\n" + "where d.doc_num = dg.doc_num \r\n" + "and d.emp_num = ?\r\n"
				+ "and d.doc_kind = ?\r\n" + "and d.doc_state = 2) a\r\n" + "where a.years = ?\r\n"
				+ "and a.months = ?)b";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(dockindcountselectSQL);
			int index = 0;
			pstmt.setString(++index, emp_num);
			pstmt.setInt(++index, doc_kind);
			pstmt.setString(++index, years);
			pstmt.setString(++index, String.format("%02d", Integer.parseInt(months)));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("aa");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return count;
	}

	// 연차의 모든것 select
	@Override
	public List<Yeoncha> selectAll(String emp_num, String years) throws Exception {
		// 2)DB와 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Yeoncha> list = new ArrayList<>();

		String yeonchaselectSQL = "select g.start_date, g.end_date, (to_date(g.end_date)-to_date(g.start_date)) use\r\n" + "from document d, doc_gigan g\r\n" + "where d.doc_num=g.doc_num\r\n"
				+ "and doc_state='2' and doc_kind=50\r\n" + "and substr(d.start_date,1,4)=?\r\n" + "and emp_num=?";

		// 3)SQL문장을 DB서버로 송신
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(yeonchaselectSQL);
			// int num = Integer.parseInt(emp_num);
			pstmt.setString(1, years);
			pstmt.setString(2, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Yeoncha yc = new Yeoncha();
				yc.setStart_date(rs.getString("start_date"));
				yc.setEnd_date(rs.getString("end_date"));
				yc.setUse_yeoncha(rs.getInt("use")+1);
				list.add(yc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return list;
	}

	@Override
	public void useupdate(String emp_num, String years) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<List<String>> giganselectAll(String emp_num, String years) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
