package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import repositorio.dados.AA;
import repositorio.dados.CN;
import repositorio.dados.Dao;
import repositorio.dados.Jaccard;
import repositorio.dados.entidades.*;

@WebServlet("/cadastrar/finalizaCadastro")
public class FinalizaCadastro extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		Jaccard j = new Jaccard();
		AA aa = new AA();
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("userid");

		try {
			j.atualizaEv(id);
			j.atualizaMat(id);
			j.atualizaEst(id);
			aa.atualizaAA(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("../home");

	}
}