package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repositorio.dados.Dao;
import repositorio.dados.entidades.*;

@WebServlet("/cadastrarMaterias/adicionaMaterias")
public class adicionaMaterias extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os parâmetros do request
		String nome = request.getParameter("nome");
		String codigo = request.getParameter("codigo");
		String centro = request.getParameter("centro");
		String idUniversidade = request.getParameter("idUniversidade");
		String idArea = request.getParameter("idArea");
		String idCurso = request.getParameter("idCurso");
		String nomeCurto = request.getParameter("nomeCurto");
		String tags = request.getParameter("tags");

		// monta um objeto contato
		Materia materia = new Materia();
		materia.setNome(nome);
		materia.setCodigo(codigo);
		materia.setCentro(centro);
		materia.setIdUniv(Integer.parseInt(idUniversidade));
		materia.setIdArea(Integer.parseInt(idArea));
		materia.setIdCurso(Integer.parseInt(idCurso));
		materia.setNomeCurto(nomeCurto);
		materia.setTags(tags);


		// salva o contato
		Dao dao = new Dao();
		try {
			dao.adiciona(materia);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<title>TAIA</title>");
		out.println("<link rel='stylesheet' href='../css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='../css/main.css'>");
		out.println("<link rel='stylesheet' href='../css/normalize.css'>");
		out.println("</head>");
		out.println("<body style='text-align:center;'>");
		out.println("<h2>Matéria " + materia.getNome() + 
				" adicionada com sucesso</h2>");
		out.println("<br /><a href='../cadastrarMaterias'><button class='btn btn-primary'>Cadastrar outro</button></a>");
		out.println("</body>");
		out.println("</html>");
	}
}