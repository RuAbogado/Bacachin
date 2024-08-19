package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.dao.CarritoDao;
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

        UserDao userDao = new UserDao();
        User user = userDao.getOne(correo, contraseña);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Obtener o crear el carrito asociado al usuario
            CarritoDao carritoDao = new CarritoDao();
            Carrito carrito = null;

            if ("cliente".equals(user.getTipo())) {
                // Obtener el ID_Cliente del usuario
                int idCliente = userDao.getClienteId(user.getId());
                carrito = carritoDao.getCarritoByClienteId(idCliente);

                // Si el carrito no existe, crear uno nuevo
                if (carrito == null) {
                    long millis = System.currentTimeMillis();
                    carrito = carritoDao.createCarrito(new Carrito(idCliente, new Date(millis)));
                    System.out.println("Nuevo ID del carrito creado: " + carrito.getID_Carrito());
                }

                session.setAttribute("carrito", carrito);
                session.setAttribute("ID_Carrito", carrito.getID_Carrito());
            }

            // Redirigir según el tipo de usuario
            if ("admin".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homeadmin.jsp");
            } else if ("cliente".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homecliente.jsp");
            } else if ("empleado".equals(user.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/homeempleados.jsp");
            } else {
                // Redirigir a una página de error o de acceso no permitido si el tipo de usuario no es reconocido
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        } else {
            req.setAttribute("mensaje", "Correo o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}