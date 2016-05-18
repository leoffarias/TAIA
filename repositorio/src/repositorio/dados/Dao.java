package repositorio.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import repositorio.dados.entidades.*;

public class Dao {

	  // a conexão com o banco de dados
	  private Connection connection;

	  public Dao() {
	    this.connection = new ConnectionFactory().getConnection();
	  }
	  
	  public void adiciona(Evento evento) throws SQLException {
		    String sql = "insert into evento " +
		            "(nome,descricao,endereco,nome_curto,tipo,id_area, dia, hora, foto, tags)" +
		            " values (?,?,?,?,?,?,?,?,?,?)";

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
		        stmt.setString(10,evento.getTags());
		        

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adiciona(Materia materia) throws SQLException {
		    String sql = "insert into materia " +
		            "(nome,codigo,nome_curto,centro,id_univ,id_area,id_curso, tags)" +
		            " values (?,?,?,?,?,?,?,?)";

		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,materia.getNome());
		        stmt.setString(2,materia.getCodigo());
		        stmt.setString(3,materia.getNomeCurto());
		        stmt.setString(4,materia.getCentro());
		        stmt.setInt(6,materia.getIdArea());
		        stmt.setInt(5,materia.getIdUniv());
		        stmt.setInt(7,materia.getIdCurso());
		        stmt.setString(8,materia.getTags());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adiciona(Universidade univ) throws SQLException {
		    String sql = "insert into universidades " +
		            "(nome,nome_curto,foto)" +
		            " values (?,?,?)";
		    
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,univ.getNome());
		        stmt.setString(3,univ.getFoto());
		        stmt.setString(2,univ.getNomeCurto());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adiciona(Curso curso) throws SQLException {
		    String sql = "insert into curso " +
		            "(nome)" +
		            " values (?)";
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,curso.getNome());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adiciona(Area area) throws SQLException {
		    String sql = "insert into area " +
		            "(nome,foto)" +
		            " values (?,?)";
		    
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,area.getNome());
		        stmt.setString(2,area.getFoto());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adiciona(Aluno aluno) throws SQLException {
		    String sql = "insert into aluno " +
		            "(nome,id_univ,id_curso, foto, tags, email, senha)" +
		            " values (?,?,?,?,?,?,?)";
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,aluno.getNome());
		        stmt.setString(4,aluno.getFoto());
		        stmt.setInt(2,aluno.getIdUniv());
		        stmt.setInt(3,aluno.getIdCurso());
		        stmt.setString(5,aluno.getTags());
		        stmt.setString(6,aluno.getEmail());
		        stmt.setString(7,aluno.getSenha());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adiciona(Estagio estagio) throws SQLException {
		    String sql = "insert into estagio " +
		            "(funcao,descricao,empresa,id_curso,site,id_area, foto, tags)" +
		            " values (?,?,?,?,?,?,?,?)";

		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,estagio.getFuncao());
		        stmt.setString(2,estagio.getDescricao());
		        stmt.setString(3,estagio.getEmpresa());
		        stmt.setInt(4,estagio.getIdCurso());
		        stmt.setString(5,estagio.getSite());
		        stmt.setInt(6,estagio.getIdArea());
		        stmt.setString(7,estagio.getFoto());
		        stmt.setString(8,estagio.getTags());
		        

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
}
