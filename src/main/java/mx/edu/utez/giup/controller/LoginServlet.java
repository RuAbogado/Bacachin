package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.dao.CarritoDao;
import mx.edu.utez.giup.dao.CarritoEmpleadoDao;
import mx.edu.utez.giup.dao.CarritoAdminDao;
import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.model.CarritoEmpleado;
import mx.edu.utez.giup.model.CarritoAdmin;
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

            int userId = user.getId();
            switch (user.getTipo()) {
                case "cliente": {
                    userId = userDao.getClienteId(user.getId());
                    CarritoDao carritoDao = new CarritoDao();
                    Carrito carrito = carritoDao.getCarritoByUsuarioId(userId, "cliente");

                    if (carrito == null) {
                        long millis = System.currentTimeMillis();
                        carrito = carritoDao.createCarrito(new Carrito(userId, new Date(millis), "cliente"));
                        System.out.println("Nuevo ID del carrito creado: " + carrito.getID_Carrito());
                    }

                    session.setAttribute("carrito", carrito);
                    session.setAttribute("ID_Carrito", carrito.getID_Carrito());
                    break;
                }
                case "empleado": {
                    userId = userDao.getEmpleadoId(user.getId());
                    CarritoEmpleadoDao carritoEmpleadoDao = new CarritoEmpleadoDao();
                    CarritoEmpleado carritoEmpleado = carritoEmpleadoDao.getCarritoByEmpleadoId(userId);

                    if (carritoEmpleado == null) {
                        long millis = System.currentTimeMillis();
                        carritoEmpleado = carritoEmpleadoDao.createCarrito(new CarritoEmpleado(userId, new Date(millis)));
                        System.out.println("Nuevo ID del carrito de empleado creado: " + carritoEmpleado.getID_Carrito_empleado());
                    }

                    session.setAttribute("carritoEmpleado", carritoEmpleado);
                    session.setAttribute("ID_CarritoEmpleado", carritoEmpleado.getID_Carrito_empleado());
                    break;
                }
                case "admin": {
                    userId = userDao.getAdminId(user.getId());
                    CarritoAdminDao carritoAdminDao = new CarritoAdminDao();
                    CarritoAdmin carritoAdmin = carritoAdminDao.getCarritoByAdminId(userId);

                    if (carritoAdmin == null) {
                        long millis = System.currentTimeMillis();
                        carritoAdmin = carritoAdminDao.createCarrito(new CarritoAdmin(userId, new Date(millis)));
                        System.out.println("Nuevo ID del carrito de admin creado: " + carritoAdmin.getID_Carrito_admin());
                    }

                    session.setAttribute("carritoAdmin", carritoAdmin);
                    session.setAttribute("ID_CarritoAdmin", carritoAdmin.getID_Carrito_admin());
                    break;
                }
                default: {
                    resp.sendRedirect(req.getContextPath() + "/error.jsp");
                    return;
                }
            }

            // Redirigir según el tipo de usuario
            switch (user.getTipo()) {
                case "admin":
                    resp.sendRedirect(req.getContextPath() + "/homeadmin.jsp");
                    break;
                case "cliente":
                    resp.sendRedirect(req.getContextPath() + "/homecliente.jsp");
                    break;
                case "empleado":
                    resp.sendRedirect(req.getContextPath() + "/homeempleados.jsp");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/error.jsp");
                    break;
            }
        } else {
            req.setAttribute("mensaje", "Correo o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}