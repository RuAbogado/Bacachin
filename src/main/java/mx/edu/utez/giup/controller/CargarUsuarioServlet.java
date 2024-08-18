package mx.edu.utez.giup.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.User;

import java.io.IOException;

@WebServlet(name = "CargarUsuarioServlet", urlPatterns = {"/cargarUsuario"})
public class CargarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String usuarioId = request.getParameter("ID_Usuario");
        UserDao userDao = new UserDao();
        Gson gson = new Gson();

        if (usuarioId != null) {
            try {
                int id = Integer.parseInt(usuarioId);
                User user = userDao.getOne(id);

                if (user != null) {
                    String userJson = gson.toJson(user);
                    response.getWriter().write(userJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Usuario no encontrado\"}");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"ID de usuario inválido\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID de usuario no proporcionado\"}");
        }
    }
}
