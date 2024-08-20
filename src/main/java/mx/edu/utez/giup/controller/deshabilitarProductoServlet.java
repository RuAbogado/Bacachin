package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.ProductosDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deshabilitarProducto")
public class deshabilitarProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int idProducto = Integer.parseInt(request.getParameter("ID_Producto"));

        ProductosDao productosDao = new ProductosDao();
        boolean success = productosDao.deshabilitarProducto(idProducto);

        if (success) {
            out.print("{\"success\":true}");
        } else {
            out.print("{\"success\":false}");
        }

        out.flush();
    }
}