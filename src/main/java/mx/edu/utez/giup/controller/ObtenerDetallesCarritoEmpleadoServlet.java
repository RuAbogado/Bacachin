package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.utez.giup.dao.DetalleCarritoEmpleadoDao;
import mx.edu.utez.giup.model.CarritoEmpleado;
import mx.edu.utez.giup.model.DetalleCarritoEmpleado;

import java.io.IOException;
import java.util.List;

@WebServlet("/ObtenerDetallesCarritoEmpleado")
public class ObtenerDetallesCarritoEmpleadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer el tipo de contenido como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        CarritoEmpleado carrito = (CarritoEmpleado) session.getAttribute("carritoEmpleado"); // Obtener el carrito desde la sesión

        if (carrito == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"No se ha encontrado el carrito en la sesión.\"}");
            return;
        }

        int idCarrito = carrito.getID_Carrito_empleado();

        // Instanciar el DAO para obtener los detalles del carrito
        DetalleCarritoEmpleadoDao detalleCarritoDao = new DetalleCarritoEmpleadoDao();
        List<DetalleCarritoEmpleado> detalles = detalleCarritoDao.getDetallesByCarritoId(idCarrito);

        // Convertir la lista de detalles a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(detalles);

        // Enviar la respuesta JSON
        response.getWriter().write(json);
    }
}