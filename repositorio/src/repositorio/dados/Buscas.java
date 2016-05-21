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

	public List<Evento> getEventos(String sql) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Evento> eventos = new ArrayList<Evento>();

			while (rs.next()) {

				// criando o objeto Contato
				Evento evento = new Evento();
				evento.setTags(rs.getString("tags"));
				evento.setId(Integer.parseInt(rs.getString("id")));

				// adicionando o objeto � lista
				eventos.add(evento);
			}
			rs.close();
			stmt.close();
			return eventos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Materia> getMaterias(String sql) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();

			while (rs.next()) {

				// criando o objeto Contato
				Materia materia = new Materia();
				materia.setTags(rs.getString("tags"));
				materia.setId(Integer.parseInt(rs.getString("id")));

				// adicionando o objeto � lista
				materias.add(materia);
			}
			rs.close();
			stmt.close();
			return materias;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Estagio> getEstagio(String sql) throws SQLException {

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Estagio> estagios = new ArrayList<Estagio>();

			while (rs.next()) {

				// criando o objeto Contato
				Estagio estagio = new Estagio();
				estagio.setTags(rs.getString("tags"));
				estagio.setId(Integer.parseInt(rs.getString("id")));

				// adicionando o objeto � lista
				estagios.add(estagio);
			}
			rs.close();
			stmt.close();
			return estagios;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
