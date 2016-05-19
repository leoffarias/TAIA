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
import repositorio.dados.entidades.Estagio;

@WebServlet("/cadastrarEstagio/adicionaEstagios")
@MultipartConfig
public class adicionaEstagios extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os parâmetros do request
		String funcao = request.getParameter("funcao");
		String empresa = request.getParameter("empresa");
		String descricao = request.getParameter("descricao");
		String idCurso = request.getParameter("idCurso");
		String idArea = request.getParameter("idArea");
		String site = request.getParameter("site");
		String tags = request.getParameter("tags");
		InputStream inputStream = null;
        Part filePart = request.getPart("foto");
        if (filePart != null) {
            inputStream = filePart.getInputStream();            
        }


		// monta um objeto contato
		Estagio estagio = new Estagio();
		estagio.setFuncao(funcao);
		estagio.setEmpresa(empresa);
		estagio.setDescricao(descricao);
		estagio.setIdArea(Integer.parseInt(idArea));
		estagio.setIdCurso(Integer.parseInt(idCurso));
		estagio.setSite(site);
		estagio.setFoto(inputStream);
		estagio.setTags(tags);


		// salva o contato
		Dao dao = new Dao();
		try {
			dao.adiciona(estagio);
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
		out.println("<h2>Estágio " + estagio.getEmpresa() + 
				" adicionado com sucesso</h2>");
		out.println("<br /><a href='../cadastrarEstagio'><button class='btn btn-primary'>Cadastrar outro</button></a>");
		out.println("</body>");
		out.println("</html>");
	}
}