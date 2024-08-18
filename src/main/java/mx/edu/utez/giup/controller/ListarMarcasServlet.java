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

@WebServlet("/ListarMarcas")
public class ListarMarcasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT ID_Marcas,Nombre,Descripcion,Estado FROM Marcas";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Nombre</th>");
            out.println("<th>Descripcion</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Modificar Estado");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (rs.next()) {
                if(rs.getString("Nombre") != null){
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("Nombre") + "</td>");
                    out.println("<td>" + rs.getString("Descripcion") + "</td>");
                    int estado = rs.getInt("Estado");
                    if (estado == 1) {
                        out.println("<td>Habilitada</td>");
                    }else if (estado == 0) {
                        out.println("<td>Deshabilitada</td>");
                    }else {
                        out.println("<td>error en la solicitud</td>");
                    }

                    int idMarcas = rs.getInt("ID_Marcas");
                    if (estado == 1) {
                        out.println("<td><button type=\"button\" onclick=\"DeshabilitarMarca(" + idMarcas + ")\">Deshabilitar</button></td>");
                    }else if (estado == 0) {
                        out.println("<td><button type=\"button\" onclick=\"HabilitarMarca(" + idMarcas + ")\">Habilitar</button></td>");
                    }else {
                        out.println("<td>error en la solicitud</td>");
                    }


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