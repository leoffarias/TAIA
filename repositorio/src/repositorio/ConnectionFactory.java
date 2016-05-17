package repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
	     try {
	         return DriverManager.getConnection(
	 "jdbc:mysql://216.172.172.220/cgnpl150_taia", "cgnpl150_taia", "aulex762");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
}
