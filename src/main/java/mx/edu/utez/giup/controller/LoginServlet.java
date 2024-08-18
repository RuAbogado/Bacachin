package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.model.Cliente;
import mx.edu.utez.giup.model.User;
import mx.edu.utez.giup.dao.ClienteDao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("contraseña");

        if (correo == null || correo.isEmpty() || contraseña == null || contraseña.isEmpty()) {
            req.setAttribute("mensaje", "Correo o contraseña no pueden estar vacíos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        UserDao userDao = new UserDao();
        User user = userDao.getOne(correo, contraseña);

        if (user == null) {
            req.setAttribute("mensaje", "Correo o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        if ("cliente".equals(user.getTipo())) {
            ClienteDao clienteDao = null;
            clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.getClienteById(user.getId());

            if (cliente != null) {
                long millis = System.currentTimeMillis();
                Carrito carrito = new Carrito(cliente.getIdCliente(), new Date(millis));
                session.setAttribute("carrito", carrito);
            } else {
                session.setAttribute("carrito", null);
            }
        }

        session.removeAttribute("mensaje");

        if ("admin".equals(user.getTipo())) {
            resp.sendRedirect(req.getContextPath() + "/homeadmin.jsp");
        } else if ("cliente".equals(user.getTipo())) {
            resp.sendRedirect(req.getContextPath() + "/homecliente.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/homeempleados.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}