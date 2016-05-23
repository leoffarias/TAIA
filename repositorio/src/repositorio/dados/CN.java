package repositorio.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CN {

	private Connection connection;

	public CN() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Integer> ligacoesEv(int id, String tabela) throws SQLException {
		List<Integer> eventos = new ArrayList<Integer>();
		String sql = "SELECT id_evento1, id_evento2 FROM "+tabela+" WHERE id_evento1 = "+id+" OR id_evento2 = "+id+";";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			int ev1 = rs.getInt("id_evento1");
			int ev2 = rs.getInt("id_evento2");

			if(ev1 != id) {
				eventos.add(ev1);
			} else {
				eventos.add(ev2);
			}
		}
		rs.close();
		stmt.close();
		return eventos;
	}
	
	public List<Integer> ligacoesUsu(int id, String tabela) throws SQLException {
		List<Integer> eventos = new ArrayList<Integer>();
		String sql = "SELECT id_usu FROM "+tabela+" WHERE id_eve = "+id+" AND peso = 1;";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int usu = rs.getInt("id_usu");
				eventos.add(usu);

		}
		rs.close();
		stmt.close();
		return eventos;
	}

	public void calcula(int id_ev1, List<Integer> eventos1, int id_ev2, String tabelaEv, String tabelaUsu) throws SQLException {
		
		List<Integer> eventos2 = ligacoesUsu(id_ev2, tabelaUsu);
		eventos2.retainAll(eventos1);
		int cn = eventos2.size();

		String sql = "UPDATE "+tabelaEv+" SET cn = '"+cn+"' WHERE id_evento1 = "+id_ev1+" AND id_evento2 = "+id_ev2+";";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException a) {
			throw new RuntimeException(a);
		}
	}

	public void atualizaCN(int id, String tabelaEv, String tabelaUsu) throws SQLException {
		List<Integer> eventos1 = ligacoesEv(id, tabelaEv);
		List<Integer> eventosUsu = ligacoesUsu(id, tabelaUsu);

		for(int i = 0; i < eventos1.size(); i++) {
			calcula(id, eventosUsu, eventos1.get(i), tabelaEv, tabelaUsu);
		}
		
		connection.close();
	}
}
