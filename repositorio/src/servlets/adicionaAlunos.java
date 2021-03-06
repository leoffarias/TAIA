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

import repositorio.dados.Dao;
import repositorio.dados.entidades.*;

@WebServlet("/cadastrar/adicionaAlunos")
@MultipartConfig
public class adicionaAlunos extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os parāmetros do request
		String nome = request.getParameter("nome");
		int idUniversidade = Integer.parseInt(request.getParameter("idUniversidade"));
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));
		String tags = request.getParameter("tags");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		InputStream inputStream = null;
        Part filePart = request.getPart("foto");
        if (filePart != null) {
            inputStream = filePart.getInputStream();            
        }

		// monta um objeto contato
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setIdUniv(idUniversidade);
		aluno.setIdCurso(idCurso);
		aluno.setFoto(inputStream);
		aluno.setTags(tags);
		aluno.setEmail(email);
		aluno.setSenha(senha);


		// salva o contato
		Dao dao = new Dao();
		try {
			int userid = dao.adiciona(aluno);
			List<Integer> attEv = new ArrayList<Integer>();
			List<Integer> attEst = new ArrayList<Integer>();
			List<Integer> attMat = new ArrayList<Integer>();
			List<Integer> attAlu = new ArrayList<Integer>();
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("attEv", attEv);
			session.setAttribute("attEst", attEst);
			session.setAttribute("attMat", attMat);
			session.setAttribute("attAlu", attAlu);
			session.setAttribute("nome", nome);
			session.setAttribute("iduniv", idUniversidade);
			session.setAttribute("idcurso", idCurso);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("../cadastrar/preferencias");

	}
}