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
		            "(nome,descricao,endereco,nome_curto,tipo,id_area, dia, hora, foto)" +
		            " values (?,?,?,?,?,?,?,?,?)";

		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,evento.getNome());
		        stmt.setString(2,evento.getDescricao());
		        stmt.setString(3,evento.getEndereco());
		        stmt.setString(4,evento.getNomeCurto());
		        stmt.setString(5,evento.getTipo());
		        stmt.setInt(6,evento.getIdArea());
		        stmt.setInt(7,evento.getDia());
		        stmt.setString(8,evento.getHora());
		        stmt.setString(9,evento.getFoto());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
}
