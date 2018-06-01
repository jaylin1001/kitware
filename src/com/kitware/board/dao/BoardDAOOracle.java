package com.kitware.board.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kitware.board.vo.NoticeBoard;
import com.kitware.sql.MyConnection;



public class BoardDAOOracle implements BoardDAO {
	
	@Override
	public int selectCount() throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectCountSQL = 
				"SELECT COUNT(*) totalcnt FROM notice_board";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("totalcnt");
			return totalCount;
		}finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<NoticeBoard> selectNoticeBoard(int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectNBSQL="select r,seq,emp_num,name,title,content,hit,to_char(log_time,'yyyy-mm-dd hh24:mi') log_time ,originfilename,savefilename , path " + 
				"from(select rownum r ,a.* " + 
				"    from(select * " + 
				"    from notice_board " + 
				"    order by seq desc)a)b " + 
				"where r between ? and ? ";
		List<NoticeBoard> list = new ArrayList<NoticeBoard>();
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectNBSQL);
			int cntPerPage=7;//1페이지별 3건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1;
			System.out.println("startRow:"+startRow);
			System.out.println("endRow:"+endRow);
			pstmt.setInt(1, startRow);	pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();		
			while(rs.next()) {
				list.add(new NoticeBoard(
						rs.getString("r"),
						rs.getString("seq"),
						rs.getString("emp_num"),
						rs.getString("name"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getString("hit"),
						rs.getString("log_time"),
						rs.getString("originfilename"),
						rs.getString("savefilename"),
						rs.getString("path")
						));
			}
			return list;
		}finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);			
		}
	}

	@Override
	public void insertNoticeBoard(NoticeBoard noticeBoard) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insertNBSQL = "INSERT INTO notice_board (seq , emp_num, name, title, content, log_time,originfilename,savefilename,path) " + 
							  "VALUES (noticeboard_seq.nextval,?,?,?,?,sysdate,?,?,?)";
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(insertNBSQL);
			pstmt.setString(1, noticeBoard.getEmp_num());
			pstmt.setString(2, noticeBoard.getName());
			pstmt.setString(3, noticeBoard.getTitle());
			pstmt.setString(4, noticeBoard.getContent());
			pstmt.setString(5, noticeBoard.getOriginFileName());
			pstmt.setString(6, noticeBoard.getSaveFileName());
			pstmt.setString(7, noticeBoard.getPath());
			pstmt.executeUpdate();

		}finally {
			MyConnection.close( pstmt, con);
		}
	}
	

	@Override
	public void updateNoticeBoard(NoticeBoard noticeBoard) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String updateNBSQL = "update notice_board\r\n" + 
				"set title= ? , content =? , log_time = sysdate, originfilename = ? ,savefilename = ? , path = ?\r\n" + 
				"where seq = ?";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateNBSQL);
			pstmt.setString(1, noticeBoard.getTitle());
			pstmt.setString(2, noticeBoard.getContent());
			pstmt.setString(3, noticeBoard.getSeq());
			pstmt.setString(4, noticeBoard.getOriginFileName());
			pstmt.setString(5, noticeBoard.getSaveFileName());
			pstmt.setString(6, noticeBoard.getPath());
			pstmt.executeUpdate();

		}finally {
			MyConnection.close( pstmt, con);
		}
	}
	

	@Override
	public void deleteNoticeBoard(String seq) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String deleteNBSQL = "delete notice_board\r\n" + 
				"where seq = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteNBSQL);
			pstmt.setString(1, seq);
			pstmt.executeUpdate();
		}finally {
			MyConnection.close( pstmt, con);
		}
	}
	
	//조회수를 1씩 증가한다.
	@Override
	public void updateHit(String hitseq) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String deleteNBSQL = "update notice_board " + 
				"set hit = hit + 1 " + 
				"where seq = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(deleteNBSQL);
			pstmt.setString(1, hitseq);
			pstmt.executeUpdate();
		}finally {
			MyConnection.close( pstmt, con);
		}
		
	}

	//테스트용 main method
	public static void main(String[] args) {
		BoardDAOOracle test = new BoardDAOOracle();
		try {
			List<NoticeBoard> list = test.selectNoticeBoard(1);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
