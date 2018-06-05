package com.kitware.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.dao.MyConnection;
import com.kitware.authorization.vo.DocGiganVO;
import com.kitware.authorization.vo.DocKindVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Mail;
import com.kitware.member.vo.Members;


public class MemberSelectDAOOracle implements MemberSelectDAO{
	
	//로그인한 사원의 개인정보 가져오기.
	@Override
	public Members selectMemberInfo(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<GradeInfo> gradeList = new ArrayList<>();
		List<DeptInfo> deptList = new ArrayList<>();
		String selectMemberInfoSQL = "select m.* , g.position_name , d.dept_name\r\n" + 
				"from members m join grade_info g\r\n" + 
				"on m.position_num = g.position_num\r\n" + 
				"join dept_info d\r\n" + 
				"on m.dept_num = d.dept_num\r\n" + 
				"where m.id = ?";
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) { //아이디가 없는경우
				return null;
			}else {
				Members mb = new Members();
				mb.setEmp_num(rs.getString("EMP_NUM"));
				mb.setDept_num(rs.getString("DEPT_NUM"));
				mb.setPosition_num(rs.getString("POSITION_NUM"));
				mb.setId(id);
				mb.setPwd(rs.getString("PWD"));
				mb.setName(rs.getString("NAME"));
				mb.setGender(rs.getString("GENDER"));
				mb.setEmail1(rs.getString("EMAIL1"));
				mb.setEmail2(rs.getString("EMAIL2"));
				mb.setTel1(rs.getString("TEL1"));
				mb.setTel2(rs.getString("TEL2"));
				mb.setTel3(rs.getString("TEL3"));
				
				GradeInfo gi = new GradeInfo(mb.getPosition_num(),rs.getString("POSITION_NAME"));
				mb.setGradeinfo(gi);
				
				DeptInfo di = new DeptInfo(mb.getDept_num(),rs.getString("DEPT_NAME"));
				mb.setDeptinfo(di);
				return mb;
			}
		} catch (SQLException e) {
			e.printStackTrace(); //톰캣콘솔
			throw e;			
		} finally {	
			com.kitware.sql.MyConnection.close(rs, pstmt, con);			
		}		
	}
	
	
	
	@Override
	public List<Members> selectAllmembers() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectMemberInfoSQL = "select m.* , g.position_name , d.dept_name\r\n"
				+ "from members m join grade_info g\r\n" + "on m.position_num = g.position_num\r\n"
				+ "join dept_info d\r\n" + "on m.dept_num = d.dept_num\r\n"
				+ "order by m.dept_num,m.position_num,m.emp_num";

		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			rs = pstmt.executeQuery();
			List<Members> list = new ArrayList<>();
			while (rs.next()) {
				Members mb = new Members();
				mb.setEmp_num(rs.getString("EMP_NUM"));
				mb.setDept_num(rs.getString("DEPT_NUM"));
				mb.setPosition_num(rs.getString("POSITION_NUM"));
				mb.setName(rs.getString("NAME"));

				GradeInfo gi = new GradeInfo(mb.getPosition_num(), rs.getString("POSITION_NAME"));
				mb.setGradeinfo(gi);

				DeptInfo di = new DeptInfo(mb.getDept_num(), rs.getString("DEPT_NAME"));
				mb.setDeptinfo(di);

				list.add(mb);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<DeptInfo> getDepartments() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectDeptInfoSQL = "SELECT *\r\n" + "FROM dept_info\r\n" + "ORDER BY dept_num";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectDeptInfoSQL);
			rs = pstmt.executeQuery();
			List<DeptInfo> list = new ArrayList<>();
			while (rs.next()) { // 아이디가 없는경우
				list.add(new DeptInfo(rs.getString("dept_num"), rs.getString("dept_name")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<GradeInfo> getGradeInfo() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectMemberInfoSQL = "SELECT *\r\n" + "FROM grade_info\r\n" + "ORDER BY position_num";

		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			rs = pstmt.executeQuery();
			List<GradeInfo> list = new ArrayList<>();
			while (rs.next()) { // 아이디가 없는경우
				list.add(new GradeInfo(rs.getString("position_num"), rs.getString("position_name")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}
	
	@Override
	public List<Members> getGradeMember(String position_num, String dept_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectMemberInfoSQL = "SELECT *\r\n" + "FROM members\r\n" + "where position_num=? and dept_num=?";

		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			pstmt.setString(1, position_num);
			pstmt.setString(2, dept_num);
			rs = pstmt.executeQuery();
			List<Members> list = new ArrayList<>();
			while (rs.next()) { // 아이디가 없는경우
				Members m = new Members();
				m.setName(rs.getString("name"));
				list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Mail> selectMailList(String emp_num) throws Exception {

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				String selectMailListSQL = "select rownum, m.mail_num, mb.name, mb2.name name2, m.mail_title, m.mail_content, m.send_date, m.watch_yn"
						+" from mail m, members mb, members mb2"
						+" where m.rcv_num = mb2.emp_num"
						+" and m.emp_num = mb.emp_num"
						+" and m.rcv_num = ? order by m.mail_num desc";
				List<Mail> maillist = new ArrayList<>(); 
				Mail mail = null; 
				Members mem = new Members();
				try {
					con = MyConnection.getConnection();
					pstmt = con.prepareStatement(selectMailListSQL);
					pstmt.setString(1, emp_num);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						mail = new Mail();
						mail.setRownum(rs.getString("rownum"));
						mail.setMail_num(rs.getString("mail_num"));
						mail.setMail_title(rs.getString("mail_title"));
						mail.setMail_content(rs.getString("mail_content"));
						mail.setSend_date(rs.getString("send_date"));
						mail.setWatch_yn(rs.getString("watch_yn"));
						mem = new Members(rs.getString("name"), rs.getString("name2"));
						mail.setMembers(mem);
						maillist.add(mail);
					}

				} finally {
					MyConnection.close(rs, pstmt, con);
				}
				return maillist;
			}
	@Override
	public List<Mail> selectMailList2(String emp_num) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectMailListSQL = "select rownum, m.mail_num, mb.name, mb2.name name2, m.mail_title, m.mail_content, m.send_date, m.watch_yn"
				+" from mail m, members mb, members mb2"
				+" where m.rcv_num = mb2.emp_num"
				+" and m.emp_num = mb.emp_num"
				+" and m.emp_num = ? order by m.mail_num desc";
		List<Mail> maillist = new ArrayList<>(); 
		Mail mail = null; 
		Members mem = new Members();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMailListSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mail = new Mail();
				mail.setRownum(rs.getString("rownum"));
				mail.setMail_num(rs.getString("mail_num"));
				mail.setMail_title(rs.getString("mail_title"));
				mail.setMail_content(rs.getString("mail_content"));
				mail.setSend_date(rs.getString("send_date"));
				mail.setWatch_yn(rs.getString("watch_yn"));
				mem = new Members(rs.getString("name"), rs.getString("name2"));
				mail.setMembers(mem);
				maillist.add(mail);
			}
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return maillist;
	}
	@Override
	public Mail selectMailList3(String emp_num) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectMailListSQL = "select count(mail_num) count"
								   +" from mail"
								   +" where rcv_num=?"
								   +" and watch_yn =0";
		Mail mail = null;
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMailListSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			mail = new Mail();
			mail.setCount(rs.getString("count"));
			pstmt.executeQuery();
			}
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return mail;
	}

	@Override
	public void insertMail(Mail mail) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insertMailSQL = "insert into mail(mail_num, emp_num, rcv_num, mail_title, mail_content)"
				+" values (mail_num_seq.nextval,?,?,?,?)";
		try {
			con = MyConnection.getConnection();
			pstmt= con.prepareStatement(insertMailSQL);
			pstmt.setString(1, mail.getEmp_num());
			pstmt.setString(2, mail.getRcv_num());
			pstmt.setString(3, mail.getMail_title());
			pstmt.setString(4, mail.getMail_content());
			pstmt.executeUpdate();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public void updateWatch(String mail_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			String updateWatchSQL ="update mail"
									+" set watch_yn = 1"
									+" where mail_num =?";
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateWatchSQL);
			pstmt.setString(1, mail_num);
			pstmt.executeUpdate();
			
		}finally {
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public Mail selectMailAll(String mail_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectMailListSQL = "select m.mail_num, mb.name, mb2.name name2, m.mail_content, m.mail_title, m.send_date, m.watch_yn"
				+" from mail m, members mb, members mb2"
				+" where m.rcv_num = mb2.emp_num"
				+" and m.emp_num = mb.emp_num"
				+" and m.mail_num = ?";
		Mail mail = null; 
		Members mem = new Members();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMailListSQL);
			pstmt.setString(1, mail_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mail = new Mail();
				mail.setMail_num(rs.getString("mail_num"));
				mail.setMail_content(rs.getString("mail_content"));
				mail.setMail_title(rs.getString("mail_title"));
				mail.setSend_date(rs.getString("send_date"));
				mail.setWatch_yn(rs.getString("watch_yn"));
				mem = new Members(rs.getString("name"), rs.getString("name2"));
				mail.setMembers(mem);
			}

		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return mail;
	}

	
	@Override
	public void updateEdit(Mail mail, String mail_num) throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String updateEditSQL = "update mail"
					+" set mail_title = ?,"
					+" MAIL_CONTENT = ?,"
					+" re_yn = '1', watch_yn = '0'"
					+" where mail_num =?";
			try {
				con = MyConnection.getConnection();
				pstmt = con.prepareStatement(updateEditSQL);
				pstmt.setString(1, mail.getMail_title());
				pstmt.setString(2, mail.getMail_content());
				pstmt.setString(3, mail_num);
				pstmt.executeUpdate();

			} finally {
				MyConnection.close(rs, pstmt, con);
			}

		}
		
	
	
	//DB TEST
		public static void main(String[] args) {
			MemberSelectDAOOracle test = new MemberSelectDAOOracle();
			try {
				Mail test1 = test.selectMailList3("2");
				System.out.println(test1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


}
