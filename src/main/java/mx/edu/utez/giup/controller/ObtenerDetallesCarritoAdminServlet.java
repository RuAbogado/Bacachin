package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.utez.giup.dao.DetalleCarritoAdminDao;
import mx.edu.utez.giup.dao.ProductosDao;
import mx.edu.utez.giup.model.DetalleCarritoAdmin;
import mx.edu.utez.giup.model.Productos;

import java.io.IOException;
import java.util.List;

@WebServlet("/ObtenerDetallesCarritoAdmin")
public class ObtenerDetallesCarritoAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Integer idCarritoAdmin = (Integer) session.getAttribute("ID_CarritoAdmin");

        if (idCarritoAdmin == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"No se ha encontrado el ID del carrito en la sesión.\"}");
            return;
        }

        DetalleCarritoAdminDao detalleCarritoDao = new DetalleCarritoAdminDao();
        ProductosDao productosDao = new ProductosDao();
        List<DetalleCarritoAdmin> detalles = null;

        try {
            // Obtén los detalles del carrito
            detalles = detalleCarritoDao.getDetallesByCarritoId(idCarritoAdmin);

            // Adjunta el nombre y el precio del producto a cada detalle
            for (DetalleCarritoAdmin detalle : detalles) {
                Productos producto = productosDao.getProductoById(detalle.getIdProducto());
                if (producto != null) {
                    detalle.setNombreProducto(producto.getNombre());
                    detalle.setPrecioProducto(producto.getPrecio());
                } else {
                    // Manejar el caso donde el producto no se encuentra
                    detalle.setNombreProducto("Producto no encontrado");
                    detalle.setPrecioProducto(0.0f);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error al obtener los detalles del carrito: " + e.getMessage() + "\"}");
            return;
        }

        // Convierte la lista de detalles a JSON y envíala como respuesta
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(detalles);
        response.getWriter().write(json);
    }
}