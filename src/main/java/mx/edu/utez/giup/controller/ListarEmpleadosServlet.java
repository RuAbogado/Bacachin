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

@WebServlet("/ListarEmpleados")
public class ListarEmpleadosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT U.ID_Usuario, U.Nombre, U.Apellido, U.Correo, U.Telefono, U.Tipo,U.Estado, E.ID_Usuario AS Empleado_IdUsuario, E.Fecha_Contratacion AS FechaContratacion, E.Salario AS Salario FROM Usuarios U JOIN Empleados E ON U.ID_Usuario = E.ID_Usuario WHERE U.Tipo = 'empleado';";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            if (!rs.next()) {
                out.println("<p>No hay usuarios registrados de tipo empledo.</p>");
                return;
            }
            rs.beforeFirst();
            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID_Usuario</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellido</th>");
            out.println("<th>Email</th>");
            out.println("<th>Teléfono</th>");
            out.println("<th>Fecha de contratacion</th>");
            out.println("<th>Salario</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Modificar Estado</th>");
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
                out.println("<td>" + rs.getString("FechaContratacion") + "</td>");
                out.println("<td>" + rs.getString("Salario") + "</td>");
                int estado = rs.getInt("Estado");
                if (estado == 1) {
                    out.println("<td>Habilitado</td>");
                }else if (estado == 0) {
                    out.println("<td>Deshabilitado</td>");
                }else {
                    out.println("<td>error en la solicitud</td>");
                }

                int ID_Usuario = rs.getInt("U.ID_Usuario");
                if (estado == 1) {
                    out.println("<td><button type=\"button\" onclick=\"DeshabilitarUsuario(" + ID_Usuario + ")\">Deshabilitar</button></td>");
                }else if (estado == 0) {
                    out.println("<td><button type=\"button\" onclick=\"HabilitarUsuario(" + ID_Usuario + ")\">Habilitar</button></td>");
                }else {
                    out.println("<td>error en la solicitud</td>");
                }
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