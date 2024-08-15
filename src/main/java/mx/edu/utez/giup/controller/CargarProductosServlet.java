package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.ProductosDao;
import mx.edu.utez.giup.model.Productos;

import java.io.IOException;
import java.util.List;
@WebServlet("/CargarProductos")
public class CargarProductosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setContentType("text/html;charset=UTF-8");

        int categoriaID;
        try {
            categoriaID = Integer.parseInt(request.getParameter("categoriaID"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
            return;
        }

        ProductosDao productosDao = new ProductosDao();
        List<Productos> productos = productosDao.cargarProductosPorCategoria(categoriaID);

        StringBuilder htmlResponse = new StringBuilder();

        if (productos.isEmpty()) {
            htmlResponse.append("<p>No hay productos disponibles en esta categoría.</p>");
        } else {
            htmlResponse.append("<table border='1' cellpadding='10' cellspacing='0'>")
                    .append("<thead>")
                    .append("<tr>")
                    .append("<th>Nombre</th>")
                    .append("<th>Descripción</th>")
                    .append("<th>Precio</th>")
                    .append("<th>Stock</th>")
                    .append("<th>Imagen</th>")
                    .append("<th>Estado</th>")
                    .append("<th>Categoria</th>")
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");

            for (Productos producto : productos) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(producto.getNombre()).append("</td>")
                        .append("<td>").append(producto.getDescripcion()).append("</td>")
                        .append("<td>$").append(String.format("%.2f", producto.getPrecio())).append("</td>")
                        .append("<td>").append(producto.getStock()).append("</td>")
                        .append("<td><img src='").append(producto.getImagen()).append("' alt='").append(producto.getNombre()).append("' class='img-fluid' width='100'></td>")
                        .append("</tr>");
            }

            htmlResponse.append("</tbody>")
                    .append("</table>");
        }

        response.getWriter().write(htmlResponse.toString());
    }
}
