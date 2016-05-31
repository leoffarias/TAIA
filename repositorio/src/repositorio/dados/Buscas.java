package repositorio.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repositorio.dados.entidades.*;

public class Buscas {

	private Connection connection;

	public Buscas() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Evento> getEventos(String sql, String tipo) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Evento> eventos = new ArrayList<Evento>();

			while (rs.next()) {

				// criando o objeto Contato
				Evento evento = new Evento();
				if(tipo.equals("grau") || tipo.equals("rec")) {
					evento.setNomeCurto(rs.getString("nome_curto"));
				}
				else if(tipo.equals("detalhes")) {
					evento.setDescricao(rs.getString("descricao"));
					evento.setNome(rs.getString("nome"));
					evento.setEndereco(rs.getString("endereco"));
					evento.setTipo(rs.getString("tipo"));
					evento.setDia(rs.getInt("dia"));
					evento.setHora(rs.getString("hora"));
				}
				evento.setId(rs.getInt("id"));
				if(!tipo.equals("rec")) {
					evento.setTags(rs.getString("tags"));
				}


				// adicionando o objeto à lista
				eventos.add(evento);
			}
			rs.close();
			stmt.close();
			return eventos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Materia> getMaterias(String sql, String tipo) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();

			while (rs.next()) {

				// criando o objeto Contato
				Materia materia = new Materia();
				if (tipo.equals("grau")) {
					materia.setNomeCurto(rs.getString("nome_curto"));
					materia.setIdUniv(rs.getInt("id_univ"));
					materia.setId(rs.getInt("id"));
					materia.setTags(rs.getString("tags"));
				} else if(tipo.equals("rec")) {
					materia.setId(rs.getInt("id"));
					materia.setNomeCurto(rs.getString("nome_curto"));
					materia.setIdUniv(rs.getInt("id_univ"));
				} else if (tipo.equals("detalhes")) {
					materia.setNome(rs.getString("nome"));
					materia.setCodigo(rs.getString("codigo"));
					materia.setCentro(rs.getString("centro"));
					materia.setIdUniv(rs.getInt("id_univ"));
					materia.setIdCurso(rs.getInt("id_curso"));
					materia.setTags(rs.getString("tags"));
				} else {
					materia.setId(rs.getInt("id"));
					materia.setTags(rs.getString("tags"));
				}

				// adicionando o objeto à lista
				materias.add(materia);
			}
			rs.close();
			stmt.close();
			return materias;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Estagio> getEstagio(String sql, String tipo) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Estagio> estagios = new ArrayList<Estagio>();

			while (rs.next()) {

				// criando o objeto Contato
				Estagio estagio = new Estagio();
				if(tipo.equals("grau")) {
					estagio.setFuncao(rs.getString("funcao"));
					estagio.setId(rs.getInt("id"));
					estagio.setTags(rs.getString("tags"));
				} else if (tipo.equals("rec")) {
					estagio.setFuncao(rs.getString("funcao"));
					estagio.setId(rs.getInt("id"));
					estagio.setEmpresa(rs.getString("empresa"));					
				} else if (tipo.equals("detalhes")) {
					estagio.setFuncao(rs.getString("funcao"));
					estagio.setEmpresa(rs.getString("empresa"));
					estagio.setDescricao(rs.getString("descricao"));
					estagio.setSite(rs.getString("site"));
					estagio.setTags(rs.getString("tags"));
					estagio.setIdCurso(rs.getInt("id_curso"));
				}
				else {
					estagio.setId(rs.getInt("id"));
					estagio.setTags(rs.getString("tags"));
				}
				// adicionando o objeto à lista
				estagios.add(estagio);
			}
			rs.close();
			stmt.close();
			return estagios;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Aluno getAluno(String sql, String tipo) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Aluno aluno = new Aluno();
			while (rs.next()) {
				if(tipo.equals("user")) {
					aluno.setNome(rs.getString("nome"));
					aluno.setId(rs.getInt("id"));
					aluno.setIdUniv(rs.getInt("id_univ"));
					aluno.setIdCurso(rs.getInt("id_curso"));
				} else if(tipo.equals("grau")) {
					aluno.setId(rs.getInt("id"));
				}
				else {
					aluno.setTags(rs.getString("tags"));
				}
			}
			rs.close();
			stmt.close();
			return aluno;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Universidade getUniv(String sql) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Universidade univ = new Universidade();
			while (rs.next()) {
					univ.setNome(rs.getString("nome"));
			}
			rs.close();
			stmt.close();
			return univ;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Curso getCurso(String sql) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Curso curso = new Curso();
			while (rs.next()) {
					curso.setNome(rs.getString("nome"));
			}
			rs.close();
			stmt.close();
			return curso;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
