package repositorio.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import repositorio.dados.entidades.*;

public class Jaccard {

	private Connection connection;
	private Buscas buscas;

	public Jaccard() {
		this.connection = new ConnectionFactory().getConnection();
		this.buscas = new Buscas();
	}

	public void atualizaEv(int idUsu) throws SQLException {
		String busca = "SELECT id, tags FROM evento";
		List<Evento> lista = buscas.getEventos(busca, "jaccard");
		String tagsUsu = buscas.getAluno("SELECT tags FROM aluno WHERE id =" + idUsu, "jaccard").getTags();
		String[] tags = tagsUsu.split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		for (int i = 0; i < lista.size(); i++) { // Iterando sobre eventos
			Evento ev2 = lista.get(i);
			String[] tags2 = ev2.getTags().split(" ");
			int nt2 = tags2.length;
			List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
			bList.retainAll(aList);
			double intersec = bList.size();
			double uniao = ((nt1 + nt2) - intersec);
			double resultado = intersec / uniao; // jaccard

			String sql = "INSERT INTO usuarios_eventos (id_usu, id_eve, peso, jaccard) VALUES(" + idUsu + ", "
					+ ev2.getId() + ", 0,"+resultado+") ON DUPLICATE KEY UPDATE jaccard="+resultado+";";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
		}
	}
	
	public void atualizaMat(int idUsu) throws SQLException {
		String busca = "SELECT id, tags FROM materia";
		List<Materia> lista = buscas.getMaterias(busca, "jaccard");
		String tagsUsu = buscas.getAluno("SELECT tags FROM aluno WHERE id =" + idUsu, "jaccard").getTags();
		String[] tags = tagsUsu.split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		for (int i = 0; i < lista.size(); i++) { // Iterando sobre eventos
			Materia ev2 = lista.get(i);
			String[] tags2 = ev2.getTags().split(" ");
			int nt2 = tags2.length;
			List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
			bList.retainAll(aList);
			double intersec = bList.size();
			double uniao = ((nt1 + nt2) - intersec);
			double resultado = intersec / uniao; // jaccard

			String sql = "INSERT INTO usuarios_materias (id_usu, id_eve, peso, jaccard) VALUES(" + idUsu + ", "
					+ ev2.getId() + ", 0,"+resultado+") ON DUPLICATE KEY UPDATE jaccard="+resultado+";";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
		}
	}
	
	public void atualizaEst(int idUsu) throws SQLException {
		String busca = "SELECT id, tags FROM estagio";
		List<Estagio> lista = buscas.getEstagio(busca, "jaccard");
		String tagsUsu = buscas.getAluno("SELECT tags FROM aluno WHERE id =" + idUsu, "jaccard").getTags();
		String[] tags = tagsUsu.split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		for (int i = 0; i < lista.size(); i++) { // Iterando sobre eventos
			Estagio ev2 = lista.get(i);
			String[] tags2 = ev2.getTags().split(" ");
			int nt2 = tags2.length;
			List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
			bList.retainAll(aList);
			double intersec = bList.size();
			double uniao = ((nt1 + nt2) - intersec);
			double resultado = intersec / uniao; // jaccard

			String sql = "INSERT INTO usuarios_estagio (id_usu, id_eve, peso, jaccard) VALUES(" + idUsu + ", "
					+ ev2.getId() + ", 0,"+resultado+") ON DUPLICATE KEY UPDATE jaccard="+resultado+";";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
		}
	}

	public void calcula(int id, Evento e) throws SQLException {

		String busca = "SELECT id, tags FROM evento WHERE id != " + id + ";";
		List<Evento> lista = buscas.getEventos(busca, "jaccard");

		String[] tags = e.getTags().split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		String sql = "insert into metricas_eventos " + "(id_evento1, id_evento2, jaccard, cn)" + " values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(4, 0);
			stmt.setInt(1, id);

			for (int i = 0; i < lista.size(); i++) { // Iterando sobre eventos
				Evento ev2 = lista.get(i);
				String[] tags2 = ev2.getTags().split(" ");
				int nt2 = tags2.length;
				List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
				bList.retainAll(aList);
				double intersec = bList.size();
				double uniao = ((nt1 + nt2) - intersec);
				double resultado = intersec / uniao; // jaccard

				stmt.setInt(2, ev2.getId());
				stmt.setDouble(3, resultado);

				stmt.execute();
			}
		} catch (SQLException a) {
			throw new RuntimeException(a);
		} finally {
			connection.close();
		}
	}

	public void calcula(int id, Materia e) throws SQLException {
		String busca = "SELECT id, tags FROM materia WHERE id != " + id + ";";
		List<Materia> lista = buscas.getMaterias(busca, "jaccard");

		String[] tags = e.getTags().split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		String sql = "insert into metricas_materia " + "(id_evento1, id_evento2, jaccard, cn)" + " values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(4, 0);
			stmt.setInt(1, id);

			for (int i = 0; i < lista.size(); i++) { // Iterando sobre eventos
				Materia ev2 = lista.get(i);
				String[] tags2 = ev2.getTags().split(" ");
				int nt2 = tags2.length;
				List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
				bList.retainAll(aList);
				double intersec = bList.size();
				double uniao = ((nt1 + nt2) - intersec);
				double resultado = intersec / uniao; // jaccard

				stmt.setInt(2, ev2.getId());
				stmt.setDouble(3, resultado);
				stmt.execute();

			}
		} catch (SQLException a) {
			throw new RuntimeException(a);
		} finally {
			connection.close();
		}
	}

	public void calcula(int id, Estagio e) throws SQLException {
		String busca = "SELECT id, tags FROM estagio WHERE id != " + id + ";";
		List<Estagio> lista = buscas.getEstagio(busca, "jaccard");

		String[] tags = e.getTags().split(" ");
		int nt1 = tags.length;

		List<String> aList = new ArrayList<String>(Arrays.asList(tags));

		String sql = "insert into metricas_estagio " + "(id_evento1, id_evento2, jaccard, cn)" + " values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(4, 0);
			stmt.setInt(1, id);

			for (int i = 0; i < lista.size(); i++) { // Iterando sobre eventos
				Estagio ev2 = lista.get(i);
				String[] tags2 = ev2.getTags().split(" ");
				int nt2 = tags2.length;
				List<String> bList = new ArrayList<String>(Arrays.asList(tags2));
				bList.retainAll(aList);
				double intersec = bList.size();
				double uniao = ((nt1 + nt2) - intersec);
				double resultado = intersec / uniao; // jaccard

				stmt.setInt(2, ev2.getId());
				stmt.setDouble(3, resultado);
				stmt.execute();

			}
		} catch (SQLException a) {
			throw new RuntimeException(a);
		} finally {
			connection.close();
		}
	}

}
