package repositorio;

import java.sql.SQLException;

import repositorio.obj.Evento;

public class Testebd {
    public static void main(String[] args) throws SQLException {
    	/*Connection connection = new ConnectionFactory().getConnection();
    	System.out.println("Conex�o aberta!");
    	connection.close();*/
    	
    	 Evento evento = new Evento();
    	 evento.setNome("SW Edu");
    	 evento.setDescricao("contato@caelum.com.br");
    	 evento.setEndereco("R. Vergueiro 3185 cj57");
    	 
    	 // grave nessa conex�o!!!
    	 Dao dao = new Dao();
    	 
    	 // m�todo elegante
    	 dao.adiciona(evento);
    	 
    	 System.out.println("Gravado!");
    }
}