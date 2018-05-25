package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConnection {
	static {
		/*1)JDBC드라이버 로드*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="kitri";
		String password="kitri";
		return 
				java.sql.DriverManager.getConnection(url, 
						                             user, 
						                             password); 

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
