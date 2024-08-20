package mx.edu.utez.giup.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/getUsuarioEmpleado")
public class getUsuarioClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ID de usuario establecido a 4 directamente
        int idUsuario = 2;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Usuarios WHERE ID_Usuario = ?")) {

            statement.setInt(2, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    JsonObject userData = new JsonObject();
                    userData.addProperty("nombre", resultSet.getString("Nombre"));
                    userData.addProperty("apellido", resultSet.getString("Apellido"));
                    userData.addProperty("username", resultSet.getString("Nombre_Usuario"));
                    userData.addProperty("telefono", resultSet.getString("Telefono"));
                    userData.addProperty("correo", resultSet.getString("Correo"));
                    userData.addProperty("estado", resultSet.getString("Estado"));

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(userData.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Usuario no encontrado\"}");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error en el servidor: " + e.getMessage() + "\"}");
        }
    }
}
