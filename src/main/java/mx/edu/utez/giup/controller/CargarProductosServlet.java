package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import mx.edu.utez.giup.dao.ProductosDao;
import mx.edu.utez.giup.model.Productos;

import java.io.IOException;
import java.util.List;

@WebServlet("/CargarProductos")
public class CargarProductosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establecer el tipo de contenido y la codificación
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Obtener los productos desde la base de datos
        ProductosDao productosDao = new ProductosDao();
        List<Productos> productos = productosDao.obtenerTodosLosProductos();

        // Convertir la lista de productos a JSON
        String productosJson = new Gson().toJson(productos);

        // Escribir el JSON en la respuesta
        response.getWriter().write(productosJson);
    }
}