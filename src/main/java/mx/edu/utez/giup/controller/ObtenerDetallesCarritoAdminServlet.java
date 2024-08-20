package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.utez.giup.dao.DetalleCarritoAdminDao;
import mx.edu.utez.giup.model.CarritoAdmin;
import mx.edu.utez.giup.model.DetalleCarritoAdmin;

import java.io.IOException;
import java.util.List;

@WebServlet("/ObtenerDetallesCarritoAdmin")
public class ObtenerDetallesCarritoAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer el tipo de contenido como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        CarritoAdmin carrito = (CarritoAdmin) session.getAttribute("carritoAdmin"); // Obtener el carrito desde la sesión

        if (carrito == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"No se ha encontrado el carrito en la sesión.\"}");
            return;
        }

        int idCarrito = carrito.getID_Carrito_admin();

        // Instanciar el DAO para obtener los detalles del carrito
        DetalleCarritoAdminDao detalleCarritoDao = new DetalleCarritoAdminDao();
        List<DetalleCarritoAdmin> detalles = detalleCarritoDao.getDetallesByCarritoId(idCarrito);

        // Convertir la lista de detalles a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(detalles);

        // Enviar la respuesta JSON
        response.getWriter().write(json);
    }
}