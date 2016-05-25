package repositorio.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AA {

	private Connection connection;

	public AA() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Integer> quaisEventos(int idUsu, String tabela) throws SQLException {
		List<Integer> eventos = new ArrayList<Integer>();
		String sql = "SELECT id_eve FROM "+tabela+" WHERE id_usu = "+idUsu+" AND peso = 1;";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int eve = rs.getInt("id_eve");
			eventos.add(eve);

		}
		rs.close();
		stmt.close();
		return eventos;
	}

	public List<Integer> usuarios(int id) throws SQLException {
		List<Integer> eventos = new ArrayList<Integer>();
		String sql = "SELECT id FROM aluno WHERE id != "+id+";";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int idUsu = rs.getInt("id");
			eventos.add(idUsu);

		}
		rs.close();
		stmt.close();
		return eventos;
	}

	public double calcula(List<Integer> ev, String tabela) throws SQLException {
		double resultado = 0.0;
		for(int i = 0; i < ev.size(); i++) {
			String sql = "SELECT COUNT(*) FROM "+tabela+" WHERE id_eve = "+ev.get(i)+" AND peso = 1;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				double vizinhos = rs.getDouble("COUNT(*)");
				vizinhos = 1/Math.log10(vizinhos);
				resultado = resultado + vizinhos;
			}
			rs.close();
			stmt.close();
		}
		return resultado;

	}

	public void atualizaAA(int id) throws SQLException {
		List<Integer> usuarios = usuarios(id);

		List<Integer> eventos1 = quaisEventos(id, "usuarios_eventos");
		List<Integer> eventos6 = quaisEventos(id, "usuarios_estagio");
		List<Integer> eventos5 = quaisEventos(id, "usuarios_materias");
		for(int i = 0; i < usuarios.size(); i++) {
			double aa = 0.0;
			List<Integer> eventos2 = quaisEventos(usuarios.get(i), "usuarios_eventos");	
			eventos2.retainAll(eventos1);
			aa += calcula(eventos2, "usuarios_eventos");
			List<Integer> eventos3 = quaisEventos(usuarios.get(i), "usuarios_estagio");	
			eventos3.retainAll(eventos6);
			aa += calcula(eventos3, "usuarios_estagio");
			List<Integer> eventos4 = quaisEventos(usuarios.get(i), "usuarios_materias");	
			eventos4.retainAll(eventos5);
			aa += calcula(eventos4, "usuarios_materias");
			String sql="";

			if(id > usuarios.get(i)) {
				sql = "INSERT INTO metricas_usuarios (id_usu1, id_usu2, aa) VALUES(" + id + ", "
						+ usuarios.get(i) +", "+aa+") ON DUPLICATE KEY UPDATE aa="+aa+";";
			} else {
				sql = "INSERT INTO metricas_usuarios (id_usu1, id_usu2, aa) VALUES(" + usuarios.get(i) + ", "
						+ id +", "+aa+") ON DUPLICATE KEY UPDATE aa="+aa+";";	
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
		}


	}
}
