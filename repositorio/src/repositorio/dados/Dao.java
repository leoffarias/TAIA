package repositorio.dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import repositorio.dados.entidades.Evento;

public class Dao {

	  // a conexão com o banco de dados
	  private Connection connection;

	  public Dao() {
	    this.connection = new ConnectionFactory().getConnection();
	  }
	  
	  public void adiciona(Evento evento) throws SQLException {
		    String sql = "insert into evento " +
		            "(nome,descricao,endereco,nome_curto,tipo,id_area, dia, hora)" +
		            " values (?,?,?,?,?,?,?,?)";

		    try {
		        // prepared statement para inserção
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        // seta os valores

		        stmt.setString(1,evento.getNome());
		        stmt.setString(2,evento.getDescricao());
		        stmt.setString(3,evento.getEndereco());
		        stmt.setString(4,evento.getNome());
		        stmt.setString(5,evento.getEndereco());
		        stmt.setInt(6,1);
		        stmt.setInt(7,20160521);
		        stmt.setString(8,"16:00");


		        // executa
		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
}
