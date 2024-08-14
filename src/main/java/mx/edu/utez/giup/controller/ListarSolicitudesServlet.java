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

@WebServlet("/ListarSolicitudes")
public class ListarSolicitudesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM Solicitudes";
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
            out.println("<th>ID_Solicitud</th>");
            out.println("<th>ID_Cliente</th>");
            out.println("<th>ID_Producto</th>");
            out.println("<th>Cantidad</th>");
            out.println("<th>Fecha_Solicitud</th>");
            out.println("<th>Estado</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("ID_Solicitud") + "</td>");
                out.println("<td>" + rs.getString("ID_Clientes") + "</td>");
                out.println("<td>" + rs.getString("ID_Productos") + "</td>");
                out.println("<td>" + rs.getString("Cantidad") + "</td>");
                out.println("<td>" + rs.getString("Fecha_Solicitud") + "</td>");
                out.println("<td>" + rs.getString("Estado") + "</td>");
                out.println("</tr>");out.println("<td>" + rs.getString("Estado") + "</td>");
                out.println("<td><button class='deshabilitar-btn' onclick='deshabilitarUsuario(" + rs.getInt("ID_Solicitud") + ")'>Estatus</button></td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
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
