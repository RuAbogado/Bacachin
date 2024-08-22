package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.SolicitudesDao;

import java.io.IOException;

@WebServlet("/ActualizarEstadoSolicitud")
public class ActualizarEstadoSolicitudServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración de la codificación para manejar caracteres especiales
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Obtener los parámetros enviados desde la solicitud
        String idSolicitudStr = request.getParameter("ID_Solicitud");
        String nuevoEstado = request.getParameter("estado");

        // Verificar que los parámetros no sean nulos o vacíos
        if (idSolicitudStr != null && !idSolicitudStr.isEmpty() && nuevoEstado != null && !nuevoEstado.isEmpty()) {
            try {
                int idSolicitud = Integer.parseInt(idSolicitudStr);
                SolicitudesDao solicitudesDao = new SolicitudesDao();

                // Actualizar el estado de la solicitud en la base de datos usando ID como int
                boolean actualizado = solicitudesDao.actualizarEstadoSolicitud(idSolicitud, nuevoEstado);

                // Enviar respuesta según el resultado de la actualización
                if (actualizado) {
                    response.getWriter().write("Estado actualizado correctamente.");
                } else {
                    response.getWriter().write("Error al actualizar el estado.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("ID de solicitud inválido.");
            }
        } else {
            response.getWriter().write("Datos de solicitud o estado inválidos.");
        }
    }
}
