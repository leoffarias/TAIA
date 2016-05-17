package repositorio;

import java.sql.Connection;
import java.sql.SQLException;

public class Testebd {
    public static void main(String[] args) throws SQLException {
    	Connection connection = new ConnectionFactory().getConnection();
    	System.out.println("Conexão aberta!");
    	connection.close();
    }
}