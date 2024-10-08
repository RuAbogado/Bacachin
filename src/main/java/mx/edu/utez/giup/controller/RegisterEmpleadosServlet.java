package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.EmpleadoDao;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.Empleado;
import mx.edu.utez.giup.model.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "RegisterEmpleadosServlet", urlPatterns = "/RegisterEmpleados")
public class RegisterEmpleadosServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los datos del formulario
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String usuario = req.getParameter("usuario");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("contraseña");

        // Crear y configurar el objeto User
        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setUsername(usuario);
        user.setTelefono(telefono);
        user.setCorreo(correo);
        user.setPassword(contraseña);
        user.setEstado(true);  // Asignar estado activo (true) al usuario
        user.setCodigo("x");  // Generar o establecer el código según tu lógica
        user.setTipo("empleado");  // Asignar tipo "empleado"

        UserDao userDao = new UserDao();

        // Capturar los datos del empleado
        int salario = Integer.parseInt(req.getParameter("salario"));
        Date fecha = Date.valueOf(req.getParameter("fecha"));

        // Crear y configurar el objeto Empleado
        Empleado empleado = new Empleado();
        empleado.setFecha_Contratacion(fecha);
        empleado.setSalario(salario);

        EmpleadoDao empleadoDao = new EmpleadoDao();

        try {
            // Registrar el usuario y obtener el ID generado
            int userId = userDao.registerUser(user);

            if (userId > 0) {
                // Si el usuario se registró con éxito, asociar al empleado
                empleado.setID_Usuario(userId);  // Aquí asignamos el ID de usuario al empleado
                empleadoDao.registrarEmpleado(empleado);

                resp.sendRedirect("homeadmin.jsp");
            } else {
                req.setAttribute("errorMessage", "Error en el registro. Por favor, intente nuevamente.");
                req.getRequestDispatcher("registroEmpleado.jsp").forward(req, resp);
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
            req.getRequestDispatcher("registroEmpleado.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}