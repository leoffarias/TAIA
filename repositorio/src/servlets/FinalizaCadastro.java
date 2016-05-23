package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import repositorio.dados.CN;
import repositorio.dados.Dao;
import repositorio.dados.entidades.*;

@WebServlet("/cadastrar/finalizaCadastro")
public class FinalizaCadastro extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
		
		CN at = new CN();
		try {
			at.atualizaCN(45, "metricas_eventos", "usuarios_eventos");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("../home");

	}
}