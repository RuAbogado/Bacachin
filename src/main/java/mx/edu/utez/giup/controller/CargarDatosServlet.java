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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/CargarDatos")
public class CargarDatosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain"); // Ajustar según el formato que desees usar
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            // Obtener categorías
            String queryCategorias = "SELECT * FROM Categorias";
            PreparedStatement psCategorias = conn.prepareStatement(queryCategorias);
            ResultSet rsCategorias = psCategorias.executeQuery();
            out.println("Categorias:");
            while (rsCategorias.next()) {
                out.println("ID_Categoria: " + rsCategorias.getInt("ID_Categoria"));
                out.println("Nombre: " + rsCategorias.getString("Nombre"));
                out.println("Descripcion: " + rsCategorias.getString("Descripcion"));
                out.println(); // Nueva línea para separar cada categoría
            }

            // Obtener productos
            String queryProductos = "SELECT * FROM Productos";
            PreparedStatement psProductos = conn.prepareStatement(queryProductos);
            ResultSet rsProductos = psProductos.executeQuery();
            out.println("Productos:");
            while (rsProductos.next()) {
                out.println("ID_Producto: " + rsProductos.getInt("ID_Producto"));
                out.println("ID_Categoria: " + rsProductos.getInt("ID_Categoria"));
                out.println("Nombre: " + rsProductos.getString("Nombre"));
                out.println("Descripcion: " + rsProductos.getString("Descripcion"));
                out.println("Precio: " + rsProductos.getDouble("Precio"));
                out.println("Stock: " + rsProductos.getInt("Stock"));
                out.println("Fecha_Creacion: " + rsProductos.getDate("Fecha_Creacion").toString());
                out.println("Tipo: " + rsProductos.getString("Tipo"));
                out.println("Imagen: " + rsProductos.getString("Imagen"));
                out.println(); // Nueva línea para separar cada producto
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Error al cargar los datos.");
        }
        out.flush();
    }
}