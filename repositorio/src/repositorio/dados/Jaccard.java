package repositorio.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import repositorio.dados.entidades.*;

public class Jaccard {

	private Connection connection;

	public Jaccard() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void calcula(int id, Evento e) throws SQLException {
		List<Evento> lista = getEventos(id);
		String[] tags = e.getTags().split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		String sql = "insert into metricas_eventos " +
				"(id_evento1, id_evento2, jaccard, cn)" +
				" values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(4,0);
			stmt.setInt(1,id);

			for (int i = 0; i < lista.size(); i++) { //Iterando sobre eventos
				Evento ev2 = lista.get(i);
				String[] tags2 = ev2.getTags().split(" ");
				int nt2 = tags2.length;
				List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
				bList.retainAll(aList);
				double intersec = bList.size();
				double uniao = ((nt1+nt2)-intersec);
				double resultado = intersec/uniao; //jaccard

				stmt.setInt(2,ev2.getId());
				stmt.setDouble(3,resultado);

				stmt.execute();
			}
		} catch (SQLException a) {
			throw new RuntimeException(a);
		} finally {
			connection.close();
		}
	}

	public List<Evento> getEventos(int id) throws SQLException {
		String sql = "SELECT id, tags FROM evento WHERE id != "+id+";";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Evento> eventos = new ArrayList<Evento>();

			while (rs.next()) {

				// criando o objeto Contato
				Evento evento = new Evento();
				evento.setTags(rs.getString("tags"));
				evento.setId(Integer.parseInt(rs.getString("id")));

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

	public void calcula(int id, Materia e) throws SQLException {
		List<Materia> lista = getMaterias(id);
		String[] tags = e.getTags().split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		String sql = "insert into metricas_materia " +
				"(id_materia1, id_materia2, jaccard, cn)" +
				" values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(4,0);
			stmt.setInt(1,id);

			for (int i = 0; i < lista.size(); i++) { //Iterando sobre eventos
				Materia ev2 = lista.get(i);
				String[] tags2 = ev2.getTags().split(" ");
				int nt2 = tags2.length;
				List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
				bList.retainAll(aList);
				double intersec = bList.size();
				double uniao = ((nt1+nt2)-intersec);
				double resultado = intersec/uniao; //jaccard

				stmt.setInt(2,ev2.getId());
				stmt.setDouble(3,resultado);
				stmt.execute();

			}
		} catch (SQLException a) {
			throw new RuntimeException(a);
		} finally {
			connection.close();
		}
	} 

	public List<Materia> getMaterias(int id) throws SQLException {
		String sql = "SELECT id, tags FROM materia WHERE id != "+id+";";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();

			while (rs.next()) {

				// criando o objeto Contato
				Materia materia = new Materia();
				materia.setTags(rs.getString("tags"));
				materia.setId(Integer.parseInt(rs.getString("id")));

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

	public void calcula(int id, Estagio e) throws SQLException {
		List<Estagio> lista = getEstagio(id);
		String[] tags = e.getTags().split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		String sql = "insert into metricas_estagio " +
				"(id_estagio1, id_estagio2, jaccard, cn)" +
				" values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(4,0);
			stmt.setInt(1,id);

			for (int i = 0; i < lista.size(); i++) { //Iterando sobre eventos
				Estagio ev2 = lista.get(i);
				String[] tags2 = ev2.getTags().split(" ");
				int nt2 = tags2.length;
				List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
				bList.retainAll(aList);
				double intersec = bList.size();
				double uniao = ((nt1+nt2)-intersec);
				double resultado = intersec/uniao; //jaccard

				stmt.setInt(2,ev2.getId());
				stmt.setDouble(3,resultado);
				stmt.execute();

			}
		} catch (SQLException a) {
			throw new RuntimeException(a);
		} finally {
			connection.close();
		}
	} 
	
	public List<Estagio> getEstagio(int id) throws SQLException {
		String sql = "SELECT id, tags FROM estagio WHERE id != "+id+";";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Estagio> estagios = new ArrayList<Estagio>();

			while (rs.next()) {

				// criando o objeto Contato
				Estagio estagio = new Estagio();
				estagio.setTags(rs.getString("tags"));
				estagio.setId(Integer.parseInt(rs.getString("id")));

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

}
