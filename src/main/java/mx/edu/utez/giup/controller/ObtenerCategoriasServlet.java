package mx.edu.utez.giup.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.CategoriasDao;
import mx.edu.utez.giup.model.Categorias;

import java.io.IOException;
import java.util.List;

@WebServlet("/ObtenerCategorias")
public class ObtenerCategoriasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CategoriasDao categoriasDao = new CategoriasDao();
        List<Categorias> categorias = categoriasDao.getAllCategorias();

        // Convertir la lista de categorías a JSON
        Gson gson = new Gson();
        String json = gson.toJson(categorias);

        // Enviar la respuesta JSON al cliente
        response.getWriter().write(json);
    }
}