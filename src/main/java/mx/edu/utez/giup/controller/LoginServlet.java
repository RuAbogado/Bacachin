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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("contraseña");

        // Validación básica
        if (correo == null || correo.isEmpty() || contraseña == null || contraseña.isEmpty()) {
            req.setAttribute("mensaje", "Correo o contraseña no pueden estar vacíos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        UserDao dao = new UserDao();
        User user = dao.getOne(correo, contraseña); // Aquí deberías comparar con la contraseña cifrada

        if (user == null) {
            req.setAttribute("mensaje", "Correo o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            // Crear o obtener sesión
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Crear carrito
            long millis = System.currentTimeMillis();
            session.setAttribute("carrito", new Carrito(user.getId(), new Date(millis)));

            session.removeAttribute("mensaje");

            // Redirección basada en el tipo de usuario
            if ("admin".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homeadmin.jsp");
            } else if ("cliente".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homecliente.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/homeempleados.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}