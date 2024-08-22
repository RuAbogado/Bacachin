package mx.edu.utez.giup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BuscarSolicitudes")
public class BuscarSolicitudesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = DatabaseConnectionManager.getConnection(); // Obtener la conexión correctamente
                String query = "SELECT s.ID_Solicitud, s.ID_Cliente, s.ID_Producto, s.Cantidad, s.Fecha_Solicitud, s.Estado, p.Nombre AS Nombre_Producto " +
                        "FROM Solicitudes s " +
                        "JOIN Productos p ON s.ID_Producto = p.ID_Producto " +
                        "WHERE p.Nombre LIKE ?";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, "%" + productName + "%");
                rs = stmt.executeQuery();

                out.println("<table border='1'>");
                out.println("<tr><th>ID Solicitud</th><th>ID Cliente</th><th>ID Producto</th><th>Nombre Producto</th><th>Cantidad</th><th>Fecha Solicitud</th><th>Estado</th><th>Acciones</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("ID_Solicitud") + "</td>");
                    out.println("<td>" + rs.getInt("ID_Cliente") + "</td>");
                    out.println("<td>" + rs.getInt("ID_Producto") + "</td>");
                    out.println("<td>" + rs.getString("Nombre_Producto") + "</td>"); // Nombre del producto
                    out.println("<td>" + rs.getInt("Cantidad") + "</td>");
                    out.println("<td>" + rs.getDate("Fecha_Solicitud") + "</td>");
                    out.println("<td>" + rs.getString("Estado") + "</td>");
                    out.println("<td><button onclick='mostrarDetalleVenta(" + rs.getInt("ID_Solicitud") + ")'>Ver Detalle</button></td>");
                    out.println("</tr>");
                }

                out.println("</table>");

            } catch (Exception e) {
                e.printStackTrace();
                out.println("Error al realizar la búsqueda.");
            } finally {
                // Cerrar recursos en el bloque finally
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
