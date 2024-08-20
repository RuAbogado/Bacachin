package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.CarritoDao;
import mx.edu.utez.giup.dao.CarritoEmpleadoDao;
import mx.edu.utez.giup.dao.CarritoAdminDao;

import java.io.IOException;

@WebServlet("/EliminarProductoCarrito")
public class EliminarProductoCarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idProducto = Integer.parseInt(req.getParameter("idProducto"));
        int idCarrito = Integer.parseInt(req.getParameter("ID_Carrito"));
        String tipoUsuario = req.getParameter("tipoUsuario");

        boolean success = false;

        try {
            if ("cliente".equals(tipoUsuario)) {
                CarritoDao carritoDao = new CarritoDao();
                success = carritoDao.eliminarProductoDelCarrito(idCarrito, idProducto);
            } else if ("empleado".equals(tipoUsuario)) {
                CarritoEmpleadoDao carritoEmpleadoDao = new CarritoEmpleadoDao();
                success = carritoEmpleadoDao.eliminarProductoDelCarrito(idCarrito, idProducto);
            } else if ("admin".equals(tipoUsuario)) {
                CarritoAdminDao carritoAdminDao = new CarritoAdminDao();
                success = carritoAdminDao.eliminarProductoDelCarrito(idCarrito, idProducto);
            }

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"success\":true}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"success\":false, \"message\":\"Error al eliminar el producto\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"success\":false, \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}