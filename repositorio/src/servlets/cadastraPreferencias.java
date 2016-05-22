package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repositorio.dados.Dao;

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

	}
}