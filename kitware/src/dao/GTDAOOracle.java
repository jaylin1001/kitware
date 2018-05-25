package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.Gunte;

public class GTDAOOracle implements GTDAO {
	public void insert(Gunte g) throws Exception{
		
	}
	
	public void ininsert(Gunte g) throws Exception{
		/*2)DB와 연결 */
		Connection con = null;
		
		/*3)SQL문장을 DB서버로 송신*/
		PreparedStatement pstmt=null;
		try {
			con = sql.MyConnection.getConnection();
			String ininsertSQL = "update gunte set in_time = TO_DATE(?) where emp_num = ?";
			pstmt = con.prepareStatement(ininsertSQL);
			pstmt.setDate(1, g.getIn_time());
			pstmt.setInt(2, g.getEmp_num());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			
		}finally {
			sql.MyConnection.close(pstmt, con);
		}
	}
	
	public void outupdate(Gunte g) throws Exception{
		/*2)DB와 연결 */
		Connection con = null;
		
		/*3)SQL문장을 DB서버로 송신*/
		PreparedStatement pstmt=null;
		try {
			con = sql.MyConnection.getConnection();
			String outupdateSQL = "update gunte set out_time = TO_DATE(?) where emp_num = ?";
			pstmt = con.prepareStatement(outupdateSQL);
			pstmt.setDate(1, g.getOut_time());
			pstmt.setInt(2, g.getEmp_num());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			
		}finally {
			sql.MyConnection.close(pstmt, con);
		}
	}
}
