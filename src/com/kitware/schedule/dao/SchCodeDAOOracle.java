package com.kitware.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kitware.schedule.dao.SchCodeDAO;
import com.kitware.schedule.vo.Schedule;
import com.kitware.sql.MyConnection;

public class SchCodeDAOOracle implements SchCodeDAO {

	//SchCodeDAO에서 오버라이딩한 메소드 구현.
	
	//DB로 접속하여 로그인한 사원의 개인일정 가져오는 SELECT 문.
	@Override
	public List<Schedule> selectPersonal(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectPersonalSQL = "select sch_no,emp_num,sch_name,sch_type,to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin, \r\n" + 
									"to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin, sch_repeat , sch_repeatcycle , sch_contents, \r\n" + 
									"sch_code,sch_useyn\r\n" + 
									"from sch_schedule\r\n" + 
									"where emp_num = ? \r\n" + 
									"and sch_useyn = 'N'\r\n" + 
									"and sch_code=0";
		List<Schedule> listSchedule = new ArrayList<>();
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectPersonalSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Schedule sc = new Schedule(rs.getString("sch_no"),rs.getString("emp_num"),rs.getString("sch_name"),rs.getString("sch_type")
										   ,rs.getString("sch_startdate"),rs.getString("sch_starthour"),rs.getString("sch_startmin")
										   ,rs.getString("sch_enddate"),rs.getString("sch_endhour"),rs.getString("sch_endmin")
										   ,rs.getString("sch_repeat"),rs.getString("sch_repeatcycle"),rs.getString("sch_contents")
										   ,rs.getString("sch_code"),rs.getString("sch_useyn"));
				listSchedule.add(sc);
			}
			return listSchedule;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	@Override
	public List<Schedule> selectPersonalToday(String id, String date) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectPersonalSQL = "select sch_no,emp_num,sch_name,sch_type,to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin, \r\n" + 
				"to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin, sch_repeat , sch_repeatcycle , sch_contents, \r\n" + 
				"sch_code,sch_useyn\r\n" + 
				"from sch_schedule\r\n" + 
				"where emp_num = ? \r\n" + 
				"and sch_useyn = 'N'\r\n" + 
				"and sch_code=0" +
				"and SCH_STARTDATE = ? \r\n" + 
				"and SCH_ENDDATE = ?";
		List<Schedule> listSchedule = new ArrayList<>();
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectPersonalSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Schedule sc = new Schedule(rs.getString("sch_no"),rs.getString("emp_num"),rs.getString("sch_name"),rs.getString("sch_type")
						,rs.getString("sch_startdate"),rs.getString("sch_starthour"),rs.getString("sch_startmin")
						,rs.getString("sch_enddate"),rs.getString("sch_endhour"),rs.getString("sch_endmin")
						,rs.getString("sch_repeat"),rs.getString("sch_repeatcycle"),rs.getString("sch_contents")
						,rs.getString("sch_code"),rs.getString("sch_useyn"));
				listSchedule.add(sc);
			}
			return listSchedule;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Schedule> selectDepartment(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectDeptlSQL = "SELECT sch_no, sc.emp_num,sch_name,sch_type, to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin,\r\n" + 
								"       to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin , sch_repeat,sch_repeatcycle,sch_contents,sch_code,sch_useyn\r\n" + 
								"from sch_schedule sc, members m\r\n" + 
								"where m.emp_num=sc.emp_num\r\n" + 
								"and sc.sch_code = 1 \r\n" + 
								"and m.dept_num = (select di.dept_num\r\n" + 
								"                  from members m, dept_info di\r\n" + 
								"                  where m.dept_num=di.dept_num\r\n" + 
								"                  and m.emp_num=?)\r\n" + 
								"and sc.sch_useyn = 'N'";
		List<Schedule> listSchedule = new ArrayList<>();
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectDeptlSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Schedule sc = new Schedule(rs.getString("sch_no"),rs.getString("emp_num"),rs.getString("sch_name"),rs.getString("sch_type")
						   ,rs.getString("sch_startdate"),rs.getString("sch_starthour"),rs.getString("sch_startmin")
						   ,rs.getString("sch_enddate"),rs.getString("sch_endhour"),rs.getString("sch_endmin")
						   ,rs.getString("sch_repeat"),rs.getString("sch_repeatcycle"),rs.getString("sch_contents")
						   ,rs.getString("sch_code"),rs.getString("sch_useyn"));
				listSchedule.add(sc);
			}
			return listSchedule;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Schedule> selectCompany(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectCompanylSQL = "select sch_no, emp_num,sch_name,sch_type, to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin,\r\n" + 
									"       to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin , sch_repeat,sch_repeatcycle,sch_contents,sch_code,sch_useyn\r\n" + 
									"from sch_schedule\r\n" + 
									"where sch_code = 2\r\n" + 
									"and sch_useyn = 'N'";
		List<Schedule> listSchedule = new ArrayList<>();
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectCompanylSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Schedule sc = new Schedule(rs.getString("sch_no"),rs.getString("emp_num"),rs.getString("sch_name"),rs.getString("sch_type")
						   ,rs.getString("sch_startdate"),rs.getString("sch_starthour"),rs.getString("sch_startmin")
						   ,rs.getString("sch_enddate"),rs.getString("sch_endhour"),rs.getString("sch_endmin")
						   ,rs.getString("sch_repeat"),rs.getString("sch_repeatcycle"),rs.getString("sch_contents")
						   ,rs.getString("sch_code"),rs.getString("sch_useyn"));
				listSchedule.add(sc);
			}
			return listSchedule;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Schedule> selectTotal(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectTotalSQL = "(select sch_no, emp_num,sch_name,sch_type, to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin,\r\n" + 
								"       to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin , sch_repeat,sch_repeatcycle,sch_contents,sch_code,sch_useyn\r\n" + 
								"from sch_schedule\r\n" + 
								"where emp_num = ? " + 
								"and sch_useyn = 'N')\r\n" + 
								"union\r\n" + 
								"(SELECT sch_no, sc.emp_num,sch_name,sch_type, to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin,\r\n" + 
								"       to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin , sch_repeat,sch_repeatcycle,sch_contents,sch_code,sch_useyn\r\n" + 
								"from sch_schedule sc, members m\r\n" + 
								"where m.emp_num=sc.emp_num\r\n" + 
								"and sc.sch_code = '1'\r\n" + 
								"and m.dept_num = (select di.dept_num\r\n" + 
								"                  from members m, dept_info di\r\n" + 
								"                  where m.dept_num=di.dept_num\r\n" + 
								"                  and m.emp_num=?)\r\n" + 
								"and sch_useyn = 'N')\r\n" + 
								"union                  \r\n" + 
								"(select sch_no, emp_num,sch_name,sch_type, to_char(sch_startdate, 'yyyy-mm-dd') sch_startdate,sch_starthour,sch_startmin,\r\n" + 
								"       to_char(sch_enddate, 'yyyy-mm-dd') sch_enddate , sch_endhour , sch_endmin , sch_repeat,sch_repeatcycle,sch_contents,sch_code,sch_useyn\r\n" + 
								"from sch_schedule\r\n" + 
								"where sch_code = '2'\r\n" + 
								"and sch_useyn = 'N')";
		List<Schedule> listSchedule = new ArrayList<>();
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectTotalSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Schedule sc = new Schedule(rs.getString("sch_no"),rs.getString("emp_num"),rs.getString("sch_name"),rs.getString("sch_type")
						   ,rs.getString("sch_startdate"),rs.getString("sch_starthour"),rs.getString("sch_startmin")
						   ,rs.getString("sch_enddate"),rs.getString("sch_endhour"),rs.getString("sch_endmin")
						   ,rs.getString("sch_repeat"),rs.getString("sch_repeatcycle"),rs.getString("sch_contents")
						   ,rs.getString("sch_code"),rs.getString("sch_useyn"));
				listSchedule.add(sc);
			}
			return listSchedule;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	public static void main(String[] args) {
		SchCodeDAOOracle test = new SchCodeDAOOracle();
		try {
			List<Schedule> testlist = test.selectPersonal("1");
			System.out.println(testlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

