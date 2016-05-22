package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import repositorio.dados.Dao;
import repositorio.dados.entidades.*;

@WebServlet("/cadastrar/cadastraPreferencias")
public class cadastraPreferencias extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_NO_CONTENT);

		System.out.println("TesteAAAAAAAAAAAAAAAAAAAAAAA");
		// pegando os parâmetros do request
		/*
		String nome = request.getParameter("nome");
		String idUniversidade = request.getParameter("idUniversidade");
		String idCurso = request.getParameter("idCurso");
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
		aluno.setIdUniv(Integer.parseInt(idUniversidade));
		aluno.setIdCurso(Integer.parseInt(idCurso));
		aluno.setFoto(inputStream);
		aluno.setTags(tags);
		aluno.setEmail(email);
		aluno.setSenha(senha);


		// salva o contato
		Dao dao = new Dao();
		try {
			dao.adiciona(aluno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("../cadastrar/preferencias");
*/
	}
}