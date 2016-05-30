package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repositorio.dados.Buscas;
import repositorio.dados.entidades.Aluno;


@WebServlet("/login")
public class Login extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Buscas buscas = new Buscas();
		String sql2 = "SELECT id, nome, id_univ, id_curso FROM aluno WHERE email = '"+email+"' AND senha = '"+senha+"';";
		Aluno aluno = null;
		try {
			aluno = buscas.getAluno(sql2, "user");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(aluno.getNome() == null) {
			   out.println("<script type=\"text/javascript\">");
			   out.println("alert('Usuário ou senha incorretos');");
			   out.println("location='index.html';");
			   out.println("</script>");
		} else {
			session.setAttribute("nome", aluno.getNome());
			session.setAttribute("userid", aluno.getId());
			session.setAttribute("iduniv", aluno.getIdUniv());
			session.setAttribute("idcurso", aluno.getIdCurso());
			
			//Tentar colocar quando inicia o sistema
			List<Integer> attEv = new ArrayList<Integer>();
			List<Integer> attEst = new ArrayList<Integer>();
			List<Integer> attMat = new ArrayList<Integer>();
			List<Integer> attAlu = new ArrayList<Integer>();
			session.setAttribute("attEv", attEv);
			session.setAttribute("attEst", attEst);
			session.setAttribute("attMat", attMat);
			session.setAttribute("attAlu", attAlu);
			
			response.sendRedirect("home");
		}
	}
}