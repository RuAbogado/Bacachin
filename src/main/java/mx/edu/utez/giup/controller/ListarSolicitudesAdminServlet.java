package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.SolicitudesDao;
import mx.edu.utez.giup.model.Solicitudes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/ListarSolicitudesAdmin")
public class ListarSolicitudesAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SolicitudesDao solicitudesDao = new SolicitudesDao();
            String productName = request.getParameter("productName");

            List<Solicitudes> solicitudesList;
            if (productName != null && !productName.trim().isEmpty()) {
                solicitudesList = solicitudesDao.buscarSolicitudesPorProducto(productName);
            } else {
                solicitudesList = solicitudesDao.obtenerTodasLasSolicitudes();
            }

            // Filtrar solicitudes que estén completadas (estado "Terminada")
            List<Solicitudes> solicitudesCompletadas = solicitudesList.stream()
                    .filter(solicitud -> "Terminada".equals(solicitud.getEstado()))
                    .collect(Collectors.toList());

            if (solicitudesCompletadas.isEmpty()) {
                out.println("<p>No hay solicitudes completadas.</p>");
                return;
            }

            out.println("<table id='solicitudTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Solicitud</th>");
            out.println("<th>Producto</th>");
            out.println("<th>Cantidad</th>");
            out.println("<th>Precio</th>");
            out.println("<th>Total</th>");
            out.println("<th>Estado</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            int contador = 1; // Inicializar un contador para generar IDs únicos

            for (Solicitudes solicitud : solicitudesCompletadas) {
                double total = calcularTotal(solicitud.getCantidad(), solicitud.getPrecio());

                // Generar un ID único para cada fila
                String uniqueId = "solicitud_" + contador;

                out.println("<tr id='" + uniqueId + "'>");
                out.println("<td>" + solicitud.getID_Solicitud() + "</td>");
                out.println("<td>" + solicitud.getNombreProducto() + "</td>");  // Obtención correcta del nombre del producto
                out.println("<td>" + solicitud.getCantidad() + "</td>");
                out.println("<td>" + solicitud.getPrecio() + "</td>");
                out.println("<td>" + total + "</td>");
                out.println("<td>");

                out.println("<select name='estado_" + solicitud.getID_Solicitud() + "' onchange='Estatus(this.value, " + solicitud.getID_Solicitud() + ")'>");
                out.println("<option value='Terminada'>Terminada</option>");
                out.println("</select>");

                out.println("</td>");
                out.println("</tr>");

                contador++; // Incrementar el contador para la siguiente fila
            }

            out.println("</tbody>");
            out.println("</table>");

            // Código JavaScript para manejar el cambio de estado
            out.println("<script>");
            out.println("function Estatus(value, ID_Solicitud) {");
            out.println("    fetch('/GIUP_war/ActualizarEstadoSolicitud', {");
            out.println("        method: 'POST',");
            out.println("        headers: {");
            out.println("            'Content-Type': 'application/x-www-form-urlencoded'");
            out.println("        },");
            out.println("        body: 'ID_Solicitud=' + ID_Solicitud + '&estado=' + encodeURIComponent(value)");
            out.println("    })");
            out.println("    .then(response => response.text())");
            out.println("    .then(data => {");
            out.println("        console.log('Estado actualizado: ' + data);");
            out.println("    })");
            out.println("    .catch(error => console.error('Error:', error));");
            out.println("}");
            out.println("</script>");
        }
    }

    // Método para calcular el total de la compra
    private double calcularTotal(int cantidad, double precio) {
        return cantidad * precio;
    }
}
