package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.model.CarritoAdmin;

import java.io.IOException;

@WebServlet("/ObtenerIdCarritoAdmin")
public class ObtenerIdCarritoAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CarritoAdmin carritoAdmin = (CarritoAdmin) session.getAttribute("carritoAdmin");

        if (carritoAdmin != null) {
            resp.getWriter().write(String.valueOf(carritoAdmin.getID_Carrito_admin()));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Carrito no encontrado para este administrador.");
        }
    }
}