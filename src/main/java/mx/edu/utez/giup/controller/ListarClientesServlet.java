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

@WebServlet("/ListarClientes")
public class ListarClientesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT ID_Usuario, Nombre, Apellido, Correo, Telefono FROM Usuarios WHERE Tipo = 'cliente'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (!rs.next()) {
                out.println("<p>No hay usuarios registrados de tipo cliente.</p>");
                return;
            }

            // Reset the cursor to the beginning of the result set
            rs.beforeFirst();

            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellido</th>");
            out.println("<th>Email</th>");
            out.println("<th>Teléfono</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("ID_Usuario") + "</td>");
                out.println("<td>" + rs.getString("Nombre") + "</td>");
                out.println("<td>" + rs.getString("Apellido") + "</td>");
                out.println("<td>" + rs.getString("Correo") + "</td>");
                out.println("<td>" + rs.getString("Telefono") + "</td>");
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