package metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class OnceConn {
	public static Connection getConn() {
		Connection conn;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/todox", "root", "");
		} catch(Exception e) { return null; }
		return conn;
	}
}
		
