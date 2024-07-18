package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.User;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("contraseña");

        UserDao dao = new UserDao();
        User user = dao.getOne(correo, contraseña);
        HttpSession session = req.getSession();

        if (user == null) {
            session.setAttribute("mensaje", "Correo o contraseña incorrectos");
            resp.sendRedirect(req.getContextPath() + "/index.html"); // Redirige al formulario de login si la autenticación falla
        } else {
            session.setAttribute("user", user);
            session.removeAttribute("mensaje");

            // Redirige según el tipo de usuario
            if ("admin".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homeadmin.html"); // Redirige a la página de inicio de administrador
            } else if ("Cliente".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homecliente.html"); // Redirige a la página de inicio de cliente
            } else {
                resp.sendRedirect(req.getContextPath() + "/index.html"); // Por si acaso no se tiene un tipo definido, redirige al login
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}