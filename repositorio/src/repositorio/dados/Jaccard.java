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
		//List<Resultado> resultados = new ArrayList<Resultado>();
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
				System.out.println("EM COMUM: "+intersec+" TAM1: "+nt1+" TAM2: "+nt2+" RESULTADO: "+resultado);

				stmt.setInt(2,ev2.getId());
				stmt.setDouble(3,resultado);

				stmt.execute();

				//resultados.add(new Resultado(ev2.getId(),resultado));
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

}
