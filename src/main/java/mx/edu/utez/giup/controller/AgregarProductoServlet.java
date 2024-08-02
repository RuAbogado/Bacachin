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

@WebServlet("/AgregarProducto")
public class AgregarProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String tipo = request.getParameter("tipo");
        String imagen = request.getParameter("imagen");

        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            String query = "INSERT INTO Productos (ID_Categoria, Nombre, Descripcion, Precio, Stock, Fecha_Creacion, Tipo, Imagen) VALUES (?, ?, ?, ?, ?, NOW(), ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCategoria);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            ps.setDouble(4, precio);
            ps.setInt(5, stock);
            ps.setString(6, tipo);
            ps.setString(7, imagen);
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