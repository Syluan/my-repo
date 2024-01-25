package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=THETD";
	public static final String USER = "luanhs";
	public static final String PW = "123";
	
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			//System.out.println("ok");
			return conn;
		} catch (SQLException e) {
			System.out.println("KET NOI THAT BAI");
			e.printStackTrace();
			return null;
		}
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//public static void main(String[] args) {
//	getConnection();
//}

}
