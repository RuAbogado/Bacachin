package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.model.User;
import java.io.IOException;
import java.sql.Date;

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
            resp.sendRedirect(req.getContextPath() + "/index.jsp?error=1"); // Pasa el parámetro de error en la URL
        } else {
            session.setAttribute("user", user);
            long millis = System.currentTimeMillis();
            session.setAttribute("carrito", new Carrito(user.getId(), new Date(millis)));
            session.removeAttribute("mensaje");

            // Redirige según el tipo de usuario
            if ("admin".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homeadmin.jsp"); // Redirige a la página de inicio de administrador
            } else if ("cliente".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homecliente.jsp"); // Redirige a la página de perfil de cliente
            } else {
                resp.sendRedirect(req.getContextPath() + "/index.jsp"); // Por si acaso no se tiene un tipo definido, redirige al login
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}