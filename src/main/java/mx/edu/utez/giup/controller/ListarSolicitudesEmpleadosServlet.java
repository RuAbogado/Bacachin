package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.DetalleSolicitudDao;
import mx.edu.utez.giup.dao.SolicitudesDao;
import mx.edu.utez.giup.model.DetalleSolicitud;
import mx.edu.utez.giup.model.Solicitudes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ListarSolicitudesEmpleados")
public class ListarSolicitudesEmpleadosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            SolicitudesDao solicitudesDao = new SolicitudesDao();
            DetalleSolicitudDao detalleSolicitudDao = new DetalleSolicitudDao();
            List<Solicitudes> solicitudesList = solicitudesDao.obtenerTodasLasSolicitudes();

            if (solicitudesList.isEmpty()) {
                out.println("<p>No hay Solicitudes.</p>");
                return;
            }

            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Solicitud</th>");
            out.println("<th>Total Productos</th>");
            out.println("<th>Fecha</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Descripción</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Solicitudes solicitud : solicitudesList) {
                List<DetalleSolicitud> detalles = detalleSolicitudDao.getDetallesBySolicitudId(solicitud.getID_Solicitud());

                int totalProductos = 0;
                for (DetalleSolicitud detalle : detalles) {
                    totalProductos += detalle.getCantidad();
                }

                out.println("<tr>");
                out.println("<td>" + solicitud.getID_Solicitud() + "</td>");
                out.println("<td>" + totalProductos + "</td>");
                out.println("<td>" + solicitud.getFecha_Solicitud() + "</td>");
                out.println("<td>");
                out.println("<select name='estado_" + solicitud.getID_Solicitud() + "' onchange='Estatus(this.value, " + solicitud.getID_Solicitud() + ")'>");
                out.println("<option value=''>Selecciona un estado</option>");
                out.println("<option value='Cancelar'" + ("Cancelar".equals(solicitud.getEstado()) ? " selected" : "") + ">Cancelar</option>");
                out.println("<option value='Proceso'" + ("Proceso".equals(solicitud.getEstado()) ? " selected" : "") + ">Proceso</option>");
                out.println("<option value='Terminada'" + ("Terminada".equals(solicitud.getEstado()) ? " selected" : "") + ">Terminada</option>");
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.println("<button type='button' onclick='mostrarDetalleVenta(" + solicitud.getID_Solicitud() + ")'>Detalle Venta</button>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");

            // Código JavaScript para manejar el botón de "Detalle Venta"
            out.println("<script>");
            out.println("function mostrarDetalleVenta(ID_Solicitud) {");
            out.println("  fetch('/DetalleVenta?ID_Solicitud=' + ID_Solicitud)");
            out.println("    .then(response => response.text())");
            out.println("    .then(data => {");
            out.println("      alert('Detalles de la venta:\\n' + data);");
            out.println("    })");
            out.println("    .catch(error => console.error('Error:', error));");
            out.println("}");
            out.println("</script>");
        }
    }
}