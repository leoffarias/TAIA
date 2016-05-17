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
import repositorio.dados.entidades.Evento;

@WebServlet("/cadastrarEventos/adicionaEventos")
public class adicionaEventos extends HttpServlet {
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
                        
        PrintWriter out = response.getWriter();
                        
        // pegando os par�metros do request
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");

        
        // monta um objeto contato
        Evento evento = new Evento();
        evento.setNome(nome);
        evento.setEndereco(endereco);
   	 	evento.setDescricao("contato@caelum.com.br");

        
        // salva o contato
        Dao dao = new Dao();
        try {
			dao.adiciona(evento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // imprime o nome do contato que foi adicionado
        out.println("<html>");
        out.println("<body>");
        out.println("Contato " + evento.getNome() + 
                " adicionado com sucesso");    
        out.println("</body>");
        out.println("</html>");
    }
}