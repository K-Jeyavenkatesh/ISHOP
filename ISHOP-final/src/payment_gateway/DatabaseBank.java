package payment_gateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseBank {
	
	private Connection con;
	
	public Connection DatabaseBank() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Muhil@123");
		return con;

	}
}
