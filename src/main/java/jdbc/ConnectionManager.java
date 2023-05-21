package jdbc;

import java.sql.*;

public class ConnectionManager {
	public static Connection getConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/nemne_insurance?serverTimezone=UTC&useSSL=false";

		String id = "root";
		String pw = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, id, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
