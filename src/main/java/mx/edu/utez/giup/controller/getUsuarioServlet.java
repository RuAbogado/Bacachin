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
        String idUsuarioParam = request.getParameter("ID_Usuario");
        if (idUsuarioParam == null || idUsuarioParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"El ID de usuario es requerido\"}");
            return;
        }

        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idUsuarioParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID de usuario inválido\"}");
            return;
        }

        String url = "jdbc:mysql://monorail.proxy.rlwy.net:54743/railway";
        String username = "root";
        String password = "pIHRdbjgWdnjjwtqPnZgjhGzpYbuZuhQ";

        // try-with-resources asegura que los recursos se cierren automáticamente
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE ID_usuario = ?")) {

            statement.setInt(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    JsonObject userData = new JsonObject();
                    userData.addProperty("Nombre", resultSet.getString("Nombre"));
                    userData.addProperty("Apellido", resultSet.getString("Apellido"));
                    userData.addProperty("Nombre_Usuario", resultSet.getString("Nombre_Usuario"));
                    userData.addProperty("Telefono", resultSet.getString("Telefono"));
                    userData.addProperty("Correo", resultSet.getString("Correo"));
                    userData.addProperty("Estado", resultSet.getString("Estado"));

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
            response.getWriter().write("{\"error\":\"Error en el servidor\"}");
        }
    }
}