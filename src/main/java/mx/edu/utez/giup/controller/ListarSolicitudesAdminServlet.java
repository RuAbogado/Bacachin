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

@WebServlet("/ListarSolicitudesAdmin")
public class ListarSolicitudesAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            SolicitudesDao solicitudesDao = new SolicitudesDao();
            List<Solicitudes> solicitudesList = solicitudesDao.obtenerTodasLasSolicitudes();

            if (solicitudesList.isEmpty()) {
                out.println("<p>No hay Solicitudes.</p>");
                return;
            }

            out.println("<table id='solicitudTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Solicitud</th>");
            out.println("<th>Estado</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Solicitudes solicitud : solicitudesList) {
                out.println("<tr>");
                out.println("<td>" + solicitud.getID_Solicitud() + "</td>");
                out.println("<td>");
                out.println("<select name='estado_" + solicitud.getID_Solicitud() + "' onchange='Estatus(this.value, " + solicitud.getID_Solicitud() + ")'>");
                out.println("<option value=''>Selecciona un estado</option>");
                out.println("<option value='Cancelar'" + ("Cancelar".equals(solicitud.getEstado()) ? " selected" : "") + ">Cancelar</option>");
                out.println("<option value='Proceso'" + ("Proceso".equals(solicitud.getEstado()) ? " selected" : "") + ">Proceso</option>");
                out.println("<option value='Terminada'" + ("Terminada".equals(solicitud.getEstado()) ? " selected" : "") + ">Terminada</option>");
                out.println("</select>");
                out.println("</td>");
                out.println("</tr>");
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
}