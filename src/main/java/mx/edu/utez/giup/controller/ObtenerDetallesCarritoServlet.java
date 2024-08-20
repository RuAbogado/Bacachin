package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.utez.giup.dao.DetalleCarritoDao;
import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.model.DetalleCarrito;
import mx.edu.utez.giup.model.Productos; // Asegúrate de que esta importación está presente

import java.io.IOException;
import java.util.List;

@WebServlet("/ObtenerDetallesCarrito")
public class ObtenerDetallesCarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer el tipo de contenido como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito"); // Obtener el carrito desde la sesión

        if (carrito == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"No se ha encontrado el carrito en la sesión.\"}");
            return;
        }

        int idCarrito = carrito.getID_Carrito();

        // Instanciar el DAO para obtener los detalles del carrito
        DetalleCarritoDao detalleCarritoDao = new DetalleCarritoDao();
        List<DetalleCarrito> detalles = detalleCarritoDao.getDetallesByCarritoId(idCarrito);

        // Cargar los productos completamente (nombre, precio, etc.) en cada detalle
        for (DetalleCarrito detalle : detalles) {
            Productos producto = detalleCarritoDao.getProductoById(detalle.getIdProducto());
            detalle.setProducto(producto);
        }

        // Convertir la lista de detalles a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(detalles);

        // Enviar la respuesta JSON
        response.getWriter().write(json);
    }
}