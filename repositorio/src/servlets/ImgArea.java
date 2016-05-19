package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import repositorio.dados.ConnectionFactory;

@WebServlet("/area/img/*")
public class ImgArea extends HttpServlet {

    // content=blob, name=varchar(255) UNIQUE.
    private static final String SQL_FIND = "SELECT foto FROM area WHERE id = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getPathInfo().substring(1);

        try (Connection connection = new ConnectionFactory().getConnection();
        		PreparedStatement statement = connection.prepareStatement(SQL_FIND)) {
            statement.setString(1, imageName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] content = resultSet.getBytes("foto");
                    response.setContentType(getServletContext().getMimeType(imageName));
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }

}