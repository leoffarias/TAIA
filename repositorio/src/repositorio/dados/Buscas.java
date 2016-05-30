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
				} else {
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
				if(tipo == "user") {
					aluno.setNome(rs.getString("nome"));
					aluno.setId(rs.getInt("id"));
					aluno.setIdUniv(rs.getInt("id_univ"));
				} else {
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

}
