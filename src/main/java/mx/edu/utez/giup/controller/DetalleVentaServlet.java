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
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ID_Solicitud = Integer.parseInt(request.getParameter("ID_Solicitud"));

        try {
            conn = DatabaseConnectionManager.getConnection();

            // Consulta para obtener los detalles de la venta
            String sql = "SELECT ID_DetalleCarrito, ID_Carrito, ID_Producto, Cantidad FROM Detalle_carrito WHERE ID_Solicitud = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_Solicitud);
            rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<div class='producto'>");
                out.println("ID Detalle Carrito: " + rs.getInt("ID_DetalleCarrito"));
                out.println("ID Carrito: " + rs.getInt("ID_Carrito"));
                out.println("ID Producto: " + rs.getInt("ID_Producto"));
                out.println("Cantidad: " + rs.getInt("Cantidad"));
                out.println("---------");
                out.println("</div>");
            }

            // Consulta para obtener los detalles de la venta de empleados
            sql = "SELECT ID_DetalleCarrito, ID_Carrito, ID_Producto, Cantidad FROM Detalle_carrito_empleado WHERE ID_Solicitud = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_Solicitud);
            rs = ps.executeQuery();

            while (rs.next()) {
                out.println("ID Detalle Carrito Empleado: " + rs.getInt("ID_DetalleCarrito"));
                out.println("ID Carrito: " + rs.getInt("ID_Carrito"));
                out.println("ID Producto: " + rs.getInt("ID_Producto"));
                out.println("Cantidad: " + rs.getInt("Cantidad"));
                out.println("---------");
            }

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

