package com.kitware.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.dao.MyConnection;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;
import com.kitware.member.vo.StatusBoard;
import com.kitware.member.vo.StatusDetailBoard;

public class MemberSelectDAOOracle implements MemberSelectDAO {

	// 로그인한 사원의 개인정보 가져오기.
	@Override
	public Members selectMemberInfo(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<GradeInfo> gradeList = new ArrayList<>();
		List<DeptInfo> deptList = new ArrayList<>();
		String selectMemberInfoSQL = "select m.* , g.position_name , d.dept_name\r\n"
				+ "from members m join grade_info g\r\n" + "on m.position_num = g.position_num\r\n"
				+ "join dept_info d\r\n" + "on m.dept_num = d.dept_num\r\n" + "where m.id = ? and memberyn = 'Y'";

		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next()) { // 아이디가 없는경우
				return null;
			} else {
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

				GradeInfo gi = new GradeInfo(mb.getPosition_num(), rs.getString("POSITION_NAME"));
				mb.setGradeinfo(gi);

				DeptInfo di = new DeptInfo(mb.getDept_num(), rs.getString("DEPT_NAME"));
				mb.setDeptinfo(di);
				return mb;
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public void insertMembers(Members members) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String modmemberSQL = "INSERT INTO members (emp_num, dept_num,position_num,id,pwd,name,gender,"
				+ "email1,email2,tel1,tel2,tel3)\r\n" + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(modmemberSQL);
			pstmt.setString(1, members.getEmp_num());
			pstmt.setString(2, members.getDept_num());
			pstmt.setString(3, members.getPosition_num());
			pstmt.setString(4, members.getId());
			pstmt.setString(5, members.getPwd());
			pstmt.setString(6, members.getName());
			pstmt.setString(7, members.getGender());
			pstmt.setString(8, members.getEmail1());
			pstmt.setString(9, members.getEmail2());
			pstmt.setString(10, members.getTel1());
			pstmt.setString(11, members.getTel2());
			pstmt.setString(12, members.getTel3());
			pstmt.executeUpdate();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public void insertMembers(MembersDetailInfo mdetail) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String mdetailSQL = "insert into members_detail\r\n" + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(mdetailSQL);
			pstmt.setString(1, mdetail.getEmp_num());
			pstmt.setString(2, mdetail.getBirth1());
			pstmt.setString(3, mdetail.getBirth2());
			pstmt.setString(4, mdetail.getBirth3());
			pstmt.setString(5, mdetail.getHire_date1());
			pstmt.setString(6, mdetail.getHire_date2());
			pstmt.setString(7, mdetail.getHire_date3());
			pstmt.setString(8, mdetail.getOut_date1());
			pstmt.setString(9, mdetail.getOut_date2());
			pstmt.setString(10, mdetail.getOut_date3());
			pstmt.setString(11, mdetail.getZip1());
			pstmt.setString(12, mdetail.getZip2());
			pstmt.setString(13, mdetail.getAddr1());
			pstmt.setString(14, mdetail.getAddr2());
			pstmt.setString(15, mdetail.getMarriage());
			pstmt.executeUpdate();
		} finally {
			MyConnection.close(pstmt, con);
		}

	}

	@Override
	public int selectCount() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectCountSQL = "SELECT COUNT(*) totalcnt FROM members";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("totalcnt");
			return totalCount;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<StatusBoard> selectStatusBoard(int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSBSQL = "select r, emp_num, dept_name, position_name, id, name, gender, email1, tel1,tel2,tel3\r\n"
				+ "from(select rownum r, a.*\r\n"
				+ "    from(select m.emp_num, di.dept_name, gi.position_name, m.id, m.name, m.gender, m.email1, m.tel1,m.tel2,m.tel3\r\n"
				+ "        from members m, dept_info di, grade_info gi\r\n"
				+ "        where m.dept_num = di.dept_num and m.memberyn='Y'\r\n" + "        and   m.position_num = gi.position_num\r\n"
				+ "        order by gi.POSITION_NUM) a)b\r\n" + "where r between ? and ?";
		List<StatusBoard> list = new ArrayList<StatusBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSBSQL);
			int cntPerPage = 10;// 1페이지별 3건씩 보여준다
			int endRow = cntPerPage * page;
			int startRow = endRow - cntPerPage + 1;
			System.out.println("startRow:" + startRow);
			System.out.println("endRow:" + endRow);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StatusBoard(rs.getString("r"), rs.getString("emp_num"), rs.getString("dept_name"),
						rs.getString("position_name"), rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("tel1") + "-" + rs.getString("tel2") + "-" + rs.getString("tel3"), null, null));
			}
			return list;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public StatusDetailBoard selectStatusBoardDetail(int emp_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSDSQL="select m.emp_num,m.dept_num,m.position_num,m.id,m.pwd,m.name,m.gender,m.email1,m.email2,m.tel1,m.tel2,m.tel3,\r\n" + 
				"md.birth1,md.birth2,md.birth3,md.hire_date1,md.hire_date2,md.hire_date3,md.out_date1,md.out_date2,md.out_date3,md.zip1,md.zip2,\r\n" + 
				"md.addr1,md.addr2,md.marriage\r\n" + 
				"from members m, members_detail md\r\n" + 
				"where m.emp_num = md.emp_num\r\n" + 
				"and m.emp_num = ?";
		List<StatusDetailBoard> list = new ArrayList<StatusDetailBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSDSQL);
			pstmt.setInt(1, emp_num);
			rs= pstmt.executeQuery();
			if (!rs.next()) { // 아이디가 없는경우
				return null;
			} else {
				StatusDetailBoard sdb = new StatusDetailBoard();						
				sdb.setEmp_num(rs.getString("emp_num"));			
				sdb.setDept_num(rs.getString("dept_num"));			
				sdb.setPosition_num(rs.getString("position_num"));		
				sdb.setId(rs.getString("id"));
				sdb.setPwd(rs.getString("pwd"));
				sdb.setName(rs.getString("name"));
				sdb.setGender(rs.getString("gender"));
				sdb.setEmail1(rs.getString("email1"));
				sdb.setEmail2(rs.getString("email2"));
				sdb.setTel1(rs.getString("tel1"));
				sdb.setTel2(rs.getString("tel2"));
				sdb.setTel3(rs.getString("tel3"));
				sdb.setBirth1(rs.getString("birth1"));
				sdb.setBirth2(rs.getString("birth2"));
				sdb.setBirth3(rs.getString("birth3"));
				sdb.setHire_date1(rs.getString("hire_date1"));
				sdb.setHire_date2(rs.getString("hire_date2"));
				sdb.setHire_date3(rs.getString("hire_date3"));
				sdb.setOut_date1(rs.getString("out_date1"));
				sdb.setOut_date2(rs.getString("out_date2"));
				sdb.setOut_date3(rs.getString("out_date2"));
				sdb.setZip1(rs.getString("zip1"));
				sdb.setZip2(rs.getString("zip2"));
				sdb.setAddr1(rs.getString("addr1"));
				sdb.setAddr2(rs.getString("addr2"));
				sdb.setMarriage(rs.getString("marriage"));		
				return sdb;
				}					
		}
		finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);			
		}		
	}
	

	@Override
	public void CorrectMembers(Members members) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String correctSql = "update members set dept_num =?, position_num=?,pwd=?,\r\n"
				+ "	name=?,gender=?,email1=?,email2=?,tel1=?,tel2=?,tel3=? where emp_num=?";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(correctSql);
			pstmt.setString(1, members.getDept_num());
			pstmt.setString(2, members.getPosition_num());
			pstmt.setString(3, members.getPwd());
			pstmt.setString(4, members.getName());
			pstmt.setString(5, members.getGender());
			pstmt.setString(6, members.getEmail1());
			pstmt.setString(7, members.getEmail2());
			pstmt.setString(8, members.getTel1());
			pstmt.setString(9, members.getTel2());
			pstmt.setString(10, members.getTel3());
			pstmt.setString(11, members.getEmp_num());
			pstmt.executeUpdate();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public void CorrectMembers(MembersDetailInfo mdetail) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String mdetailSQL = "update members_detail set birth1=?,birth2=?,birth3=?,hire_date1=?,hire_date2=?,hire_date3=?,\r\n"
				+ "out_date1=?,out_date2=?,out_date3=?,zip1=?,zip2=?,addr1=?,addr2=?,marriage=? where emp_num=?";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(mdetailSQL);
			pstmt.setString(1, mdetail.getBirth1());
			pstmt.setString(2, mdetail.getBirth2());
			pstmt.setString(3, mdetail.getBirth3());
			pstmt.setString(4, mdetail.getHire_date1());
			pstmt.setString(5, mdetail.getHire_date2());
			pstmt.setString(6, mdetail.getHire_date3());
			pstmt.setString(7, mdetail.getOut_date1());
			pstmt.setString(8, mdetail.getOut_date2());
			pstmt.setString(9, mdetail.getOut_date3());
			pstmt.setString(10, mdetail.getZip1());
			pstmt.setString(11, mdetail.getZip2());
			pstmt.setString(12, mdetail.getAddr1());
			pstmt.setString(13, mdetail.getAddr2());
			pstmt.setString(14, mdetail.getMarriage());
			pstmt.setString(15, mdetail.getEmp_num());
			pstmt.executeUpdate();
		} finally {
			MyConnection.close(pstmt, con);
		}

	}	

	@Override
	public void deleteMembers(String emp_num) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String deleteSQL="update members set memberyn='N' where emp_num=?";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setString(1, emp_num);
			pstmt.executeUpdate();
		}finally {
			MyConnection.close(pstmt, con);			
		}		
	}

	@Override
	public List<StatusBoard> selecEnum(int page, String enumsearch) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSBSQL = "select r, emp_num, dept_name, position_name, id, name, gender, email1, tel1,tel2,tel3\r\n"
				+ "from(select rownum r, a.*\r\n"
				+ "    from(select m.emp_num, di.dept_name, gi.position_name, m.id, m.name, m.gender, m.email1, m.tel1,m.tel2,m.tel3\r\n"
				+ "        from members m, dept_info di, grade_info gi\r\n"
				+ "        where m.dept_num = di.dept_num and m.memberyn='Y' and m.emp_num=?\r\n" + "        and   m.position_num = gi.position_num\r\n"
				+ "        order by gi.POSITION_NUM) a)b\r\n" + "where r between ? and ?";
		List<StatusBoard> list = new ArrayList<StatusBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSBSQL);
			int cntPerPage = 10;// 1페이지별 3건씩 보여준다
			int endRow = cntPerPage * page;
			int startRow = endRow - cntPerPage + 1;
			System.out.println("startRow:" + startRow);
			System.out.println("endRow:" + endRow);
			pstmt.setString(1,enumsearch);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StatusBoard(rs.getString("r"), rs.getString("emp_num"), rs.getString("dept_name"),
						rs.getString("position_name"), rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("tel1") + "-" + rs.getString("tel2") + "-" + rs.getString("tel3"), null, null));
			}
			return list;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<StatusBoard> selectDept(int page, String deptsearch2) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSBSQL = "select r, emp_num, dept_name, position_name, id, name, gender, email1, tel1,tel2,tel3\r\n"
				+ "from(select rownum r, a.*\r\n"
				+ "    from(select m.emp_num, di.dept_name, gi.position_name, m.id, m.name, m.gender, m.email1, m.tel1,m.tel2,m.tel3\r\n"
				+ "        from members m, dept_info di, grade_info gi\r\n"
				+ "        where m.dept_num = di.dept_num and m.memberyn='Y' and m.dept_num=?\r\n" + "        and   m.position_num = gi.position_num\r\n"
				+ "        order by gi.POSITION_NUM) a)b\r\n" + "where r between ? and ?";
		List<StatusBoard> list = new ArrayList<StatusBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSBSQL);
			int cntPerPage = 10;// 1페이지별 3건씩 보여준다
			int endRow = cntPerPage * page;
			int startRow = endRow - cntPerPage + 1;
			System.out.println("startRow:" + startRow);
			System.out.println("endRow:" + endRow);
			pstmt.setString(1,deptsearch2);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StatusBoard(rs.getString("r"), rs.getString("emp_num"), rs.getString("dept_name"),
						rs.getString("position_name"), rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("tel1") + "-" + rs.getString("tel2") + "-" + rs.getString("tel3"), null, null));
			}
			return list;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<StatusBoard> selectGrade(int page, String grsearch2) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSBSQL = "select r, emp_num, dept_name, position_name, id, name, gender, email1, tel1,tel2,tel3\r\n"
				+ "from(select rownum r, a.*\r\n"
				+ "    from(select m.emp_num, di.dept_name, gi.position_name, m.id, m.name, m.gender, m.email1, m.tel1,m.tel2,m.tel3\r\n"
				+ "        from members m, dept_info di, grade_info gi\r\n"
				+ "        where m.dept_num = di.dept_num and m.memberyn='Y' and m.position_num=?\r\n" + "        and   m.position_num = gi.position_num\r\n"
				+ "        order by gi.POSITION_NUM) a)b\r\n" + "where r between ? and ?";
		List<StatusBoard> list = new ArrayList<StatusBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSBSQL);
			int cntPerPage = 10;// 1페이지별 3건씩 보여준다
			int endRow = cntPerPage * page;
			int startRow = endRow - cntPerPage + 1;
			System.out.println("startRow:" + startRow);
			System.out.println("endRow:" + endRow);
			pstmt.setString(1,grsearch2);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StatusBoard(rs.getString("r"), rs.getString("emp_num"), rs.getString("dept_name"),
						rs.getString("position_name"), rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("tel1") + "-" + rs.getString("tel2") + "-" + rs.getString("tel3"), null, null));
			}
			return list;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<StatusBoard> selectId(int page, String idsearch) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSBSQL = "select r, emp_num, dept_name, position_name, id, name, gender, email1, tel1,tel2,tel3\r\n"
				+ "from(select rownum r, a.*\r\n"
				+ "    from(select m.emp_num, di.dept_name, gi.position_name, m.id, m.name, m.gender, m.email1, m.tel1,m.tel2,m.tel3\r\n"
				+ "        from members m, dept_info di, grade_info gi\r\n"
				+ "        where m.dept_num = di.dept_num and m.memberyn='Y' and m.id=?\r\n" + "        and   m.position_num = gi.position_num\r\n"
				+ "        order by gi.POSITION_NUM) a)b\r\n" + "where r between ? and ?";
		List<StatusBoard> list = new ArrayList<StatusBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSBSQL);
			int cntPerPage = 10;// 1페이지별 3건씩 보여준다
			int endRow = cntPerPage * page;
			int startRow = endRow - cntPerPage + 1;
			System.out.println("startRow:" + startRow);
			System.out.println("endRow:" + endRow);
			pstmt.setString(1,idsearch);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StatusBoard(rs.getString("r"), rs.getString("emp_num"), rs.getString("dept_name"),
						rs.getString("position_name"), rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("tel1") + "-" + rs.getString("tel2") + "-" + rs.getString("tel3"), null, null));
			}
			return list;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<StatusBoard> selectName(int page, String namesearch) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSBSQL = "select r, emp_num, dept_name, position_name, id, name, gender, email1, tel1,tel2,tel3\r\n"
				+ "from(select rownum r, a.*\r\n"
				+ "    from(select m.emp_num, di.dept_name, gi.position_name, m.id, m.name, m.gender, m.email1, m.tel1,m.tel2,m.tel3\r\n"
				+ "        from members m, dept_info di, grade_info gi\r\n"
				+ "        where m.dept_num = di.dept_num and m.memberyn='Y' and m.name=?\r\n" + "        and   m.position_num = gi.position_num\r\n"
				+ "        order by gi.POSITION_NUM) a)b\r\n" + "where r between ? and ?";
		List<StatusBoard> list = new ArrayList<StatusBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSBSQL);
			int cntPerPage = 10;// 1페이지별 3건씩 보여준다
			int endRow = cntPerPage * page;
			int startRow = endRow - cntPerPage + 1;
			System.out.println("startRow:" + startRow);
			System.out.println("endRow:" + endRow);
			pstmt.setString(1,namesearch);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new StatusBoard(rs.getString("r"), rs.getString("emp_num"), rs.getString("dept_name"),
						rs.getString("position_name"), rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("tel1") + "-" + rs.getString("tel2") + "-" + rs.getString("tel3"), null, null));
			}
			return list;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}		
	}
	public static void main(String[] args) {
		MemberSelectDAOOracle test = new MemberSelectDAOOracle();
		
		try {
			List<StatusBoard> list =test.selectName(1,"김지웅");
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
