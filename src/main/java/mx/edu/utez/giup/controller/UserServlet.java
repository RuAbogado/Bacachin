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

@WebServlet(name = "UserServlet", value = "/usuario")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDao dao = new UserDao();
        User user = dao.getOne(username, password);
        HttpSession session = req.getSession();

        if (user == null || user.getUsername() == null) {
            session.setAttribute("mensaje", "El usuario no existe en la base de datos");
            resp.sendRedirect("index.jsp");
        } else {
            session.setAttribute("user", user);
            session.removeAttribute("mensaje");
            resp.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}