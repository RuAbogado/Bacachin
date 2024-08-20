package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String usuario = req.getParameter("usuario");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("contraseña");

        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setUsername(usuario);
        user.setTelefono(telefono);
        user.setCorreo(correo);
        user.setPassword(contraseña);
        user.setEstado(true);  // Configurar el estado por defecto como activo
        user.setCodigo("x");  // Configuración del código predeterminado
        user.setTipo("cliente");  // Establecer el tipo de usuario como "cliente"

        UserDao userDao = new UserDao();

        try {
            // Registrar el usuario y obtener el ID generado
            int userId = userDao.registerUser(user);

            if (userId > 0) {
                // Si el usuario se registró con éxito
                resp.sendRedirect("index.jsp");
            } else {
                // Si hubo un problema durante el registro
                req.setAttribute("errorMessage", "Error en el registro. Por favor, intente nuevamente.");
                req.getRequestDispatcher("registro.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Correo duplicado")) {
                req.setAttribute("errorMessage", "Correo ya está en uso");
            } else if (errorMessage.contains("Teléfono duplicado")) {
                req.setAttribute("errorMessage", "Teléfono ya está registrado");
            } else if (errorMessage.contains("Nombre de usuario duplicado")) {
                req.setAttribute("errorMessage", "Nombre de usuario ya está en uso");
            } else {
                req.setAttribute("errorMessage", "Error desconocido: " + errorMessage);
            }
            req.getRequestDispatcher("registro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}