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

@WebServlet("/ListarCategorias")
public class ListarCategoriasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT Nombre,Descripcion FROM Categorias";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Nombre</th>");
            out.println("<th>Descripcion</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (rs.next()) {
                if(rs.getString("Nombre") != null){
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("Nombre") + "</td>");
                    out.println("<td>" + rs.getString("Descripcion") + "</td>");
                    out.println("</tr>");
                }

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