package mx.edu.utez.giup.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/getUsuario")
public class getUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("ID_Usuario"));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos
            String url = "jdbc:mysql://monorail.proxy.rlwy.net:54743/railway";
            String username = "root";
            String password = "pIHRdbjgWdnjjwtqPnZgjhGzpYbuZuhQ";
            connection = DriverManager.getConnection(url, username, password);

            // Ejecutar la consulta
            String sql = "SELECT * FROM usuarios WHERE ID_usuario = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            resultSet = statement.executeQuery();

            // Si encuentra el usuario
            if (resultSet.next()) {
                // Crear el objeto JSON con los datos del usuario
                JsonObject userData = new JsonObject();
                userData.addProperty("Nombre", resultSet.getString("Nombre"));
                userData.addProperty("Apellido", resultSet.getString("Apellido"));
                userData.addProperty("Nombre_Usuario", resultSet.getString("Nombre_Usuario"));
                userData.addProperty("Telefono", resultSet.getString("Telefono"));
                userData.addProperty("Correo", resultSet.getString("Correo"));
                userData.addProperty("Estado", resultSet.getString("Estado"));

                // Devolver la respuesta JSON
                response.setContentType("application/json");
                response.getWriter().write(userData.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            // Cerrar recursos
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
