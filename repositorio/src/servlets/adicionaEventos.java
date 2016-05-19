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
import repositorio.dados.entidades.Evento;

@WebServlet("/cadastrarEventos/adicionaEventos")
@MultipartConfig
public class adicionaEventos extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os par�metros do request
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String descricao = request.getParameter("descricao");
		String tipo = request.getParameter("tipo");
		String idArea = request.getParameter("idArea");
		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String ano = request.getParameter("ano");
		String hora = request.getParameter("hora");
		String nomeCurto = request.getParameter("nomeCurto");
		String tags = request.getParameter("tags");
		InputStream inputStream = null;
        Part filePart = request.getPart("foto");
        if (filePart != null) {
            inputStream = filePart.getInputStream();            
        }
        String data=ano+mes+dia;
		// monta um objeto contato
		Evento evento = new Evento();
		evento.setNome(nome);
		evento.setEndereco(endereco);
		evento.setDescricao(descricao);
		evento.setTipo(tipo);
		evento.setIdArea(Integer.parseInt(idArea));
		evento.setDia(Integer.parseInt(data));
		evento.setHora(hora);
		evento.setNomeCurto(nomeCurto);
		evento.setFoto(inputStream);
		evento.setTags(tags);

		// salva o contato
		Dao dao = new Dao();
		try {
			dao.adiciona(evento);
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
		out.println("<h2>Evento " + evento.getNome() + 
				" adicionado com sucesso</h2>");
		out.println("<br /><a href='../cadastrarEventos'><button class='btn btn-primary'>Cadastrar outro</button></a>");
		out.println("</body>");
		out.println("</html>");
	}
}