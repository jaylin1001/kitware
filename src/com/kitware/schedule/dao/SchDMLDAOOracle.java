package com.kitware.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kitware.schedule.vo.Schedule;

import com.kitware.sql.MyConnection;

public class SchDMLDAOOracle implements SchDMLDAO {
	//SchDMLDAO에서 오버라이딩한 메소드 구현.
	
	@Override
	public void insertSchedule(Schedule schedule) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insertScheduleSQL = "insert into sch_schedule(sch_no,emp_num,sch_name,sch_type,sch_startdate,sch_starthour,sch_startmin,sch_enddate,sch_endhour\r\n" + 
				"            ,sch_endmin,sch_repeat,sch_repeatcycle,sch_contents,sch_code)\r\n" + 
				"values(sch_no_seq.nextval, ?, ?, ?, to_date(?, 'yyyy-mm-dd'),?,?,\r\n" + 
				" to_date(?, 'yyyy-mm-dd'),?,?,?,?,?,?)";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertScheduleSQL);
			pstmt.setString(1, schedule.getEmp_num());
			pstmt.setString(2, schedule.getSch_name());
			pstmt.setString(3, schedule.getSch_type());
			pstmt.setString(4, schedule.getSch_startdate());
			pstmt.setString(5, schedule.getSch_starthour());
			pstmt.setString(6, schedule.getSch_startmin());
			pstmt.setString(7, schedule.getSch_enddate());
			pstmt.setString(8, schedule.getSch_endhour());
			pstmt.setString(9, schedule.getSch_endmin());
			pstmt.setString(10, schedule.getSch_repeat());
			pstmt.setString(11, schedule.getSch_repeatcycle());
			pstmt.setString(12, schedule.getSch_contents());
			pstmt.setString(13, schedule.getSch_code());
			pstmt.executeUpdate();

		}finally {
			MyConnection.close( pstmt, con);
		}
	}

	@Override
	public void updateSchedule(Schedule schedule) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String updateScheduleSQL = "update sch_schedule " + 
				"set sch_name = ? , sch_type = ? , sch_startdate = to_date(?, 'yyyy-mm-dd') , " + 
				"    sch_starthour = ?, sch_startmin = ?, sch_enddate = to_date(?, 'yyyy-mm-dd'), sch_endhour = ? , sch_endmin = ?, " + 
				"    sch_contents = ? " + 
				"where sch_no = ?";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateScheduleSQL);
			pstmt.setString(1, schedule.getSch_name());
			pstmt.setString(2, schedule.getSch_type());
			pstmt.setString(3, schedule.getSch_startdate());
			pstmt.setString(4, schedule.getSch_starthour());
			pstmt.setString(5, schedule.getSch_startmin());
			pstmt.setString(6, schedule.getSch_enddate());
			pstmt.setString(7, schedule.getSch_endhour());
			pstmt.setString(8, schedule.getSch_endmin());
			pstmt.setString(9, schedule.getSch_contents());
			pstmt.setString(10, schedule.getSch_no());
			pstmt.executeUpdate();

		}finally {
			MyConnection.close( pstmt, con);
		}
	}

	@Override
	public void deleteSchedule(Schedule schedule) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String deleteScheduleSQL = "update sch_schedule " + 
				"set sch_useyn = 'Y' " + 
				"where sch_no = ? ";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteScheduleSQL);
			pstmt.setString(1, schedule.getSch_no());
			pstmt.executeUpdate();
		}finally {
			MyConnection.close(pstmt,con);
		}
	}

	@Override
	public void insertQuickSchedule(Schedule schedule) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String qinsertScheduleSQL = "insert into sch_schedule(sch_no,emp_num,sch_name,sch_type,sch_startdate,sch_enddate,sch_code)\r\n" + 
				"values(sch_no_seq.nextval, ?, ?, ?,to_date(?, 'yyyy-mm-dd'),\r\n" + 
				" to_date(?, 'yyyy-mm-dd'),?)";
		

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(qinsertScheduleSQL);
			pstmt.setString(1, schedule.getEmp_num());
			pstmt.setString(2, schedule.getSch_name());
			pstmt.setString(3, "업무");
			pstmt.setString(4, schedule.getSch_startdate());
			pstmt.setString(5, schedule.getSch_enddate());
			pstmt.setString(6, schedule.getSch_code());
			pstmt.executeUpdate();
		}finally {
			MyConnection.close( pstmt, con);
		}
	}
}
