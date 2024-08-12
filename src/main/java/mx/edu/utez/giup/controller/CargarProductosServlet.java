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
        response.setContentType("text/html;charset=UTF-8");

        int categoriaID = Integer.parseInt(request.getParameter("categoriaID"));

        ProductosDao productosDao = new ProductosDao();
        List<Productos> productos = productosDao.cargarProductosPorCategoria(categoriaID);

        StringBuilder htmlResponse = new StringBuilder();

        for (Productos producto : productos) {
            htmlResponse.append("<div class='producto'>")
                    .append("<h3>").append(producto.getNombre()).append("</h3>")
                    .append("<p>").append(producto.getDescripcion()).append("</p>")
                    .append("<p class='precio'>Precio: $").append(String.format("%.2f", producto.getPrecio())).append("</p>")
                    .append("<p>Stock: ").append(producto.getStock()).append("</p>")
                    .append("<img src='").append(producto.getImagen()).append("' alt='").append(producto.getNombre()).append("' class='img-fluid'>")
                    .append("</div>");
        }

        response.getWriter().write(htmlResponse.toString());
    }
}