package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.ProductosDao;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.utez.giup.model.Productos;

@WebServlet("/ObtenerProductoPorId")
public class ObtenerProductoPorIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer el tipo de contenido como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Obtener el ID del producto de los parámetros de la solicitud
        String idProductoStr = request.getParameter("idProducto");
        if (idProductoStr == null || idProductoStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID del producto no proporcionado.\"}");
            return;
        }

        int idProducto;
        try {
            idProducto = Integer.parseInt(idProductoStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID del producto inválido.\"}");
            return;
        }

        // Obtener el producto desde el DAO
        ProductosDao productoDao = new ProductosDao();
        Productos producto = productoDao.getProductoById(idProducto);

        if (producto == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Producto no encontrado.\"}");
            return;
        }

        // Convertir el producto a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(producto);

        // Enviar la respuesta JSON
        response.getWriter().write(json);
    }
}