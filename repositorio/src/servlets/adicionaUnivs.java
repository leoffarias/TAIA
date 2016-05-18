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
import repositorio.dados.entidades.Universidade;

@WebServlet("/cadastrarUniv/adicionaUnivs")
public class adicionaUnivs extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os parāmetros do request
		String nome = request.getParameter("nome");
		String nomeCurto = request.getParameter("nomeCurto");
		String foto = request.getParameter("foto");

		// monta um objeto contato
		Universidade univ = new Universidade();
		univ.setNome(nome);
		univ.setNomeCurto(nomeCurto);
		univ.setFoto(foto);

		// salva o contato
		Dao dao = new Dao();
		try {
			dao.adiciona(univ);
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
		out.println("<h2>Universidade " + univ.getNome() + 
				" adicionada com sucesso</h2>");
		out.println("<br /><a href='../cadastrarUniv'><button class='btn btn-primary'>Cadastrar outro</button></a>");
		out.println("</body>");
		out.println("</html>");
	}
}