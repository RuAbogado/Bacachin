
package mx.edu.utez.giup.controller;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.ProductosDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/ObtenerIdProducto")
public class ObtenerIdProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductosDao productosDao = new ProductosDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String nombreProducto = request.getParameter("nombre");

        int idProducto = productosDao.obtenerIdPorNombre(nombreProducto);
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("idProducto", idProducto);
        out.write(jsonResponse.toString());
    }
}