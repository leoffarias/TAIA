package repositorio.tarefas;

import java.io.IOException;
import java.io.InputStream;
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


import repositorio.dados.CN;


@WebServlet("/rodaAlgoritmo")
public class RodaAlgoritmo extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		List<Integer> attEv = (ArrayList<Integer>) session.getAttribute("attEv");
		List<Integer> attEst = (ArrayList<Integer>) session.getAttribute("attEst");
		List<Integer> attMat = (ArrayList<Integer>) session.getAttribute("attMat");
		CN cn = new CN();
		
		for(int i = 0; i < attEv.size(); i++) {
			try {
				System.out.println("Agora: "+attEv.get(i));
				cn.atualizaCN(attEv.get(i), "metricas_eventos", "usuarios_eventos");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < attEst.size(); i++) {
			try {
				cn.atualizaCN(attEst.get(i), "metricas_estagio", "usuarios_estagio");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < attMat.size(); i++) {
			try {
				cn.atualizaCN(attMat.get(i), "metricas_materia", "usuarios_materias");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(attEv.size() > 0) {
		attEv.clear();
		session.setAttribute("attEv", attEv);
		}
		if(attEst.size() > 0) {
		attEst.clear();
		session.setAttribute("attEst", attEst);
		}
		if(attMat.size() > 0) {
		attMat.clear();
		session.setAttribute("attMat", attMat);
		}
		
		out.println("Atualizado com sucesso");
	}
}
	
