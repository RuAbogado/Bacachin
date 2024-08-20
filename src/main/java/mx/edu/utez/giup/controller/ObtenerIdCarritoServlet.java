package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.model.Carrito;

import java.io.IOException;

@WebServlet("/ObtenerIdCarrito")
public class ObtenerIdCarritoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Obtener la sesión actual sin crear una nueva si no existe

        if (session != null) {
            Integer idCarrito = (Integer) session.getAttribute("ID_Carrito"); // Ahora se espera que sea un Integer

            if (idCarrito != null) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(idCarrito.toString()); // Envía el ID del carrito como texto
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found si no hay ID de carrito en la sesión
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized si no hay sesión activa
        }
    }
}