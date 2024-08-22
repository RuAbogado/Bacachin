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
import java.math.BigDecimal;

@WebServlet("/DetalleVenta")
public class DetalleVentaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idSolicitudParam = request.getParameter("ID_Solicitud");

        if (idSolicitudParam == null || idSolicitudParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El ID de la solicitud es requerido. Asegúrese de que el parámetro 'ID_Solicitud' esté presente en la solicitud.");
            return;
        }

        int idSolicitud;
        try {
            idSolicitud = Integer.parseInt(idSolicitudParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "El ID de la solicitud debe ser un número válido. El formato proporcionado no es correcto.");
            return;
        }

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PrintWriter out = response.getWriter()) {

            // Consulta para obtener el nombre del cliente y el ID del usuario
            String clienteSql = "SELECT c.Nombre, s.ID_Usuario FROM Clientes c " +
                    "JOIN Solicitudes s ON c.ID_Cliente = s.ID_Cliente WHERE s.ID_Solicitud = ?";
            try (PreparedStatement ps = conn.prepareStatement(clienteSql)) {
                ps.setInt(1, idSolicitud);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String clienteNombre = rs.getString("Nombre");
                        int idUsuario = rs.getInt("ID_Usuario");
                        out.println("<h3>Cliente: " + clienteNombre + "</h3>");
                        out.println("<p>ID Usuario: " + idUsuario + "</p>");
                    } else {
                        out.println("<p>No se encontró el cliente para la solicitud con ID: " + idSolicitud + "</p>");
                        return;
                    }
                }
            }

            // Consulta para obtener los detalles de la venta y calcular el total
            String detallesSql = "SELECT p.Nombre, ds.Cantidad, p.Precio FROM Detalles_Solicitudes ds " +
                    "JOIN Productos p ON ds.ID_Producto = p.ID_Producto " +
                    "WHERE ds.ID_Solicitud = ?";
            BigDecimal totalCompra = BigDecimal.ZERO;

            try (PreparedStatement ps = conn.prepareStatement(detallesSql)) {
                ps.setInt(1, idSolicitud);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        out.println("<table border='1'>");
                        out.println("<tr><th>Producto</th><th>Cantidad</th><th>Precio</th><th>Total</th></tr>");
                        do {
                            int cantidad = rs.getInt("Cantidad");
                            BigDecimal precio = rs.getBigDecimal("Precio");
                            BigDecimal totalProducto = precio.multiply(BigDecimal.valueOf(cantidad));
                            totalCompra = totalCompra.add(totalProducto);

                            out.println("<tr>");
                            out.println("<td>" + rs.getString("Nombre") + "</td>");
                            out.println("<td>" + cantidad + "</td>");
                            out.println("<td>" + precio + "</td>");
                            out.println("<td>" + totalProducto + "</td>");
                            out.println("</tr>");
                        } while (rs.next());
                        out.println("</table>");
                    } else {
                        out.println("<p>No se encontraron detalles de venta para la solicitud con ID: " + idSolicitud + "</p>");
                    }
                }
            }

            // Mostrar el total de la compra
            out.println("<h4>Total de la compra: " + totalCompra + "</h4>");

        } catch (SQLException e) {
            log("Error al obtener detalles de la venta", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Ocurrió un error al obtener los detalles de la venta. Por favor, intente nuevamente más tarde.");
        }
    }
}
