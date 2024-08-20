package mx.edu.utez.giup.controller;

import mx.edu.utez.giup.utis.DatabaseConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/DetalleVenta")
public class DetalleVentaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ID_Solicitud = Integer.parseInt(request.getParameter("ID_Solicitud"));

        try {
            conn = DatabaseConnectionManager.getConnection();

            // Consulta para obtener el nombre del cliente
            String clienteSql = "SELECT c.Nombre FROM Clientes c JOIN Solicitudes s ON c.ID_Cliente = s.ID_Cliente WHERE s.ID_Solicitud = ?";
            ps = conn.prepareStatement(clienteSql);
            ps.setInt(1, ID_Solicitud);
            rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h3>Cliente: " + rs.getString("Nombre") + "</h3>");
            }

            // Consulta para obtener los detalles de la venta
            String detallesSql = "SELECT p.Nombre, ds.Cantidad, ds.Precio FROM Detalles_Solicitudes ds " +
                    "JOIN Productos p ON ds.ID_Producto = p.ID_Producto " +
                    "WHERE ds.ID_Solicitud = ?";
            ps = conn.prepareStatement(detallesSql);
            ps.setInt(1, ID_Solicitud);
            rs = ps.executeQuery();

            out.println("<table>");
            out.println("<tr><th>Producto</th><th>Cantidad</th><th>Precio</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("Nombre") + "</td>");
                out.println("<td>" + rs.getInt("Cantidad") + "</td>");
                out.println("<td>" + rs.getBigDecimal("Precio") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}