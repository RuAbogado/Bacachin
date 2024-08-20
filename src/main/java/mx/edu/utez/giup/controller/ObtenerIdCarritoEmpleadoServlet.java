package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.model.CarritoEmpleado;

import java.io.IOException;

@WebServlet("/ObtenerIdCarritoEmpleado")
public class ObtenerIdCarritoEmpleadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CarritoEmpleado carritoEmpleado = (CarritoEmpleado) session.getAttribute("carritoEmpleado");

        if (carritoEmpleado != null) {
            resp.getWriter().write(String.valueOf(carritoEmpleado.getID_Carrito_empleado()));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Carrito no encontrado para este empleado.");
        }
    }
}