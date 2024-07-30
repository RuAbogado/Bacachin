package mx.edu.utez.giup.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.UserDao;
import mx.edu.utez.giup.model.User;

import java.io.IOException;

@WebServlet(name = "UserServlet2", value = "/usuario2")
public class UserServlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("usuarioId"));

        UserDao dao = new UserDao();
        User user = dao.getOne(id);

        Gson gson = new Gson();
        String json = gson.toJson(user);
        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}