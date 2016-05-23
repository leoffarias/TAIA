package repositorio.dados;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
		        stmt.setBlob(9,evento.getFoto());
		        stmt.setString(10,evento.getTags());
		        

		        stmt.execute();
		        
		    	ResultSet id = stmt.executeQuery("select last_insert_id() as last_id");
		    	int lastId = -1;
		    	while (id.next()) {
		    	lastId = id.getInt(1);
		    	}
		    	stmt.close();
		    	
		    	Jaccard j = new Jaccard();
		    	j.calcula(lastId, evento);
 
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
		        
		    	ResultSet id = stmt.executeQuery("select last_insert_id() as last_id");
		    	int lastId = -1;
		    	while (id.next()) {
		    	lastId = id.getInt(1);
		    	}
		    	stmt.close();
		    	
		    	Jaccard j = new Jaccard();
		    	j.calcula(lastId, materia);
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
		        stmt.setString(2,univ.getNomeCurto());
		        InputStream is = univ.getFoto();
		        if (is != null) {
		        stmt.setBlob(3,is);
		        }

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
		        stmt.setBlob(2,area.getFoto());

		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public List<Area> getAreas() throws SQLException {
		    String sql = "SELECT nome, id FROM area;";
		    
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);
		        ResultSet rs = stmt.executeQuery();

		        List<Area> areas = new ArrayList<Area>();

		        while (rs.next()) {

		            // criando o objeto Contato
		            Area area = new Area();
		            area.setNome(rs.getString("nome"));
		            area.setId(rs.getInt("id"));

		            // adicionando o objeto à lista
		            areas.add(area);
		        }

		        rs.close();
		        stmt.close();

		        return areas;

		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public List<Curso> getCursos() throws SQLException {
		    String sql = "SELECT nome, id FROM curso;";
		    
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);
		        ResultSet rs = stmt.executeQuery();

		        List<Curso> cursos = new ArrayList<Curso>();

		        while (rs.next()) {

		            // criando o objeto Contato
		            Curso curso = new Curso();
		            curso.setNome(rs.getString("nome"));
		            curso.setId(rs.getInt("id"));

		            // adicionando o objeto à lista
		            cursos.add(curso);
		        }

		        rs.close();
		        stmt.close();

		        return cursos;

		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public List<Universidade> getUnivs() throws SQLException {
		    String sql = "SELECT nome, id FROM universidades;";
		    
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);
		        ResultSet rs = stmt.executeQuery();

		        List<Universidade> univs = new ArrayList<Universidade>();

		        while (rs.next()) {

		            // criando o objeto Contato
		        	Universidade univ = new Universidade();
		            univ.setNome(rs.getString("nome"));
		            univ.setId(rs.getInt("id"));

		            // adicionando o objeto à lista
		            univs.add(univ);
		        }

		        rs.close();
		        stmt.close();

		        return univs;

		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public int adiciona(Aluno aluno) throws SQLException {
		    String sql = "insert into aluno " +
		            "(nome,id_univ,id_curso, foto, email, senha)" +
		            " values (?,?,?,?,?,?)";
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setString(1,aluno.getNome());
		        stmt.setBlob(4,aluno.getFoto());
		        stmt.setInt(2,aluno.getIdUniv());
		        stmt.setInt(3,aluno.getIdCurso());
		        stmt.setString(5,aluno.getEmail());
		        stmt.setString(6,aluno.getSenha());

		        stmt.execute();
		        
		    	ResultSet id = stmt.executeQuery("select last_insert_id() as last_id");
		    	int lastId = -1;
		    	while (id.next()) {
		    	lastId = id.getInt(1);
		    	}
		    	stmt.close();
		    	return lastId;
		    	
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
		        stmt.setBlob(7,estagio.getFoto());
		        stmt.setString(8,estagio.getTags());
		        

		        stmt.execute();
		        
		    	ResultSet id = stmt.executeQuery("select last_insert_id() as last_id");
		    	int lastId = -1;
		    	while (id.next()) {
		    	lastId = id.getInt(1);
		    	}
		    	stmt.close();
		    	
		    	Jaccard j = new Jaccard();
		    	j.calcula(lastId, estagio);
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void adicionaAresta(int userid, int evid, String sql) throws SQLException {


		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        stmt.setInt(1,userid);
		        stmt.setInt(2,evid);
		        stmt.setInt(3,1);
		        stmt.setDouble(4,0.0);       

		        stmt.execute();
		    	stmt.close();
		    	
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
	  
	  public void modificaAluno(int userid, String tags) throws SQLException {
		  String sql = "UPDATE aluno SET tags = '"+tags+"' WHERE id = "+userid+";";
		    try {
		        PreparedStatement stmt = connection.prepareStatement(sql); 

		        stmt.execute();
		    	stmt.close();
		    	
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		           connection.close();
	         }
		}
}
