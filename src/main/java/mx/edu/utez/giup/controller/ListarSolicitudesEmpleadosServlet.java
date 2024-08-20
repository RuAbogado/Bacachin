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

@WebServlet("/ListarSolicitudesEmpleados")
public class ListarSolicitudesEmpleadosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM Ventas";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            if (!rs.next()) {
                out.println("<p>No hay Solicitudes.</p>");
                return;
            }
            rs.beforeFirst();
            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Solicitud</th>");
            out.println("<th>Total</th>");
            out.println("<th>Fecha</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Descripción</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("ID_Solicitud") + "</td>");
                out.println("<td>" + rs.getString("Total") + "</td>");
                out.println("<td>" + rs.getString("Fecha_Venta") + "</td>");
                out.println("<td>");
                out.println("<select name='estado_" + rs.getInt("ID_Solicitud") + "' onchange='Estatus(this.value, " + rs.getInt("ID_Solicitud") + ")'>");
                out.println("<option value=''>Selecciona un estado</option>");
                out.println("<option value='cancelar'" + ("Cancelar".equals(rs.getString("Estado")) ? " selected" : "") + ">Cancelar</option>");
                out.println("<option value='proceso'" + ("Proceso".equals(rs.getString("Estado")) ? " selected" : "") + ">Proceso</option>");
                out.println("<option value='terminada'" + ("Terminada".equals(rs.getString("Estado")) ? " selected" : "") + ">Terminada</option>");
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.println("<button type='button' onclick='mostrarDetalleVenta(" + rs.getInt("ID_Solicitud") + ")'>Detalle Venta</button>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");

            // Código JavaScript para manejar el botón de "Detalle Venta"
            out.println("<script>");
            out.println("function mostrarDetalleVenta(ID_Solicitud) {");
            out.println("  // Realiza una petición AJAX para obtener los detalles de la venta");
            out.println("  fetch('/DetalleVenta?ID_solicitud=' + ID_Solicitud)");
            out.println("    .then(response => response.text())");
            out.println("    .then(data => {");
            out.println("      // Aquí puedes mostrar los detalles en un modal o en un div");
            out.println("      alert('Detalles de la venta:\\n' + data);");
            out.println("    })");
            out.println("    .catch(error => console.error('Error:', error));");
            out.println("}");
            out.println("</script>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
