package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AgregarCategoria")
public class AgregarCategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            String query = "INSERT INTO Categorias (Nombre, Descripcion) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                out.print("{\"success\":true}");
            } else {
                out.print("{\"success\":false}");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"success\":false}");
        }

        out.flush();
    }
}