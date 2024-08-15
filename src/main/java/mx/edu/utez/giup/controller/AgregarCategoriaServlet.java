package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.CategoriasDao;
import mx.edu.utez.giup.model.Categorias;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AgregarCategoria")
public class AgregarCategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoriasDao categoriasDao = new CategoriasDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Categorias nuevaCategoria = new Categorias();
        nuevaCategoria.setNombre(nombre);
        nuevaCategoria.setDescripcion(descripcion);

        int categoriaId = categoriasDao.addCategoria(nuevaCategoria);

        if (categoriaId != -1) {
            out.print("{\"success\": true, \"message\": \"Categoría agregada exitosamente.\", \"ID_Categoria\": " + categoriaId + "}");
        } else {
            out.print("{\"success\": false, \"message\": \"Error al agregar la categoría.\"}");
        }

        out.flush();
    }
}