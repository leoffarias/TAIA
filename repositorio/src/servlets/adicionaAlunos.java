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

@WebServlet("/cadastrar/adicionaAlunos")
@MultipartConfig
public class adicionaAlunos extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os parâmetros do request
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

		out.println("<html>");
		out.println("<head>");
		out.println("<title>TAIA</title>");
		out.println("<link rel='stylesheet' href='../css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='../css/main.css'>");
		out.println("<link rel='stylesheet' href='../css/normalize.css'>");
		out.println("</head>");
		out.println("<body style='text-align:center;'>");
		out.println("<h2>Aluno(a) " + aluno.getNome() + 
				" adicionado(a) com sucesso</h2>");
		out.println("<br /><a href='../cadastrar'><button class='btn btn-primary'>Cadastrar outro</button></a>");
		out.println("</body>");
		out.println("</html>");
	}
}