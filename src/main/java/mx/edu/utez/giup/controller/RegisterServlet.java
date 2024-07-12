package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.User;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String usuario = req.getParameter("usuario");
        String telefono = req.getParameter("telefono");
        String sexo = req.getParameter("sexo");
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("contraseña");

        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setUsername(usuario);
        user.setTelefono(telefono);
        user.setSexo(sexo);
        user.setCorreo(correo);
        user.setPassword(contraseña);
        user.setEstado(true);  // or set as per your logic
        user.setCodigo("some_code");  // generate or set the code as per your logic

        UserDao userDao = new UserDao();
        boolean isRegistered = userDao.registerUser(user);

        if (isRegistered) {
            resp.sendRedirect("registroExitoso.jsp");
        } else {
            req.setAttribute("errorMessage", "Error en el registro. Por favor, intente nuevamente.");
            req.getRequestDispatcher("registroPersona.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}