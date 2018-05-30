package com.kitware.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MyConnection {
	/*static {
		1)JDBC드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/
	public static Connection getConnection() throws SQLException , NamingException , ClassNotFoundException{
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:/comp/env/jdbc/myoracle");
		Connection conn = ds.getConnection();
		return conn;
	}
	public static void close(PreparedStatement pstmt, Connection con) {
		close(null, pstmt, con);
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if(pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if(con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
	}
}
