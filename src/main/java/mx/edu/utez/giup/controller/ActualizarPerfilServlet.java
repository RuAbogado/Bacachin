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

@WebServlet(name = "ActualizarPerfil", value = "/ActualizarPerfil")
public class ActualizarPerfilServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Obtener datos del formulario
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String telefono = req.getParameter("telefono");
        String contraseña = req.getParameter("contraseña");

        // Actualizar los datos del usuario
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setTelefono(telefono);
        user.setPassword(contraseña); // Asegúrate de manejar la encriptación de la contraseña adecuadamente

        // Llamar al DAO para actualizar en la base de datos
        UserDao dao = new UserDao();
        boolean actualizado = dao.actualizarUsuario(user);

        if (actualizado) {
            session.setAttribute("user", user); // Actualizar los datos en la sesión
            resp.sendRedirect(req.getContextPath() + "/perfil.jsp"); // Redirigir de nuevo a la página de perfil
        } else {
            // Manejar el error si la actualización no fue exitosa
            session.setAttribute("mensaje", "Error al actualizar el perfil");
            resp.sendRedirect(req.getContextPath() + "/perfil.jsp");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}