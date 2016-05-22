package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repositorio.dados.Buscas;
import repositorio.dados.Dao;
import repositorio.dados.entidades.Aluno;
import repositorio.dados.entidades.Estagio;
import repositorio.dados.entidades.Evento;
import repositorio.dados.entidades.Materia;

@WebServlet("/cadastrar/cadastraPreferencias")
public class CadastraPreferencias extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		int evid = Integer.parseInt(request.getParameter("evid"));
		String tipo = request.getParameter("tipo");
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		String sql = "insert into "+tipo+
				" (id_usu, id_eve, peso, jaccard)" +
				" values (?,?,?,?)";
		Dao dao = new Dao();
		try {
			dao.adicionaAresta(userid, evid, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Pegar as tags do usuario e do evento, misturar e colocar no usuario
		String tagsEve = request.getParameter("tags");
		String[] tags = tagsEve.split(" ");
		Buscas buscas = new Buscas();
		String sql2 = "SELECT tags FROM aluno WHERE id = "+userid+";";
		Aluno aluno = null;
		try {
			aluno = buscas.getAluno(sql2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String tagsUsu = aluno.getTags();
		if(tagsUsu == null) {
		tagsUsu = "";
		}

		//Ver se j� tem
		boolean mudou = false;
		for(int i = 0; i < tags.length; i++) {
			if (tagsUsu.indexOf(tags[i]) < 0) {
				tagsUsu = tagsUsu+" "+tags[i];
				mudou = true;
			}
		}
		
		//Insere no bd
		if (mudou) {
			try {
				dao = new Dao();
				dao.modificaAluno(userid, tagsUsu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
}