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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String Nombre = request.getParameter("Nombre");
        String Descripcion = request.getParameter("Descripcion");

        Categorias nuevaCategoria = new Categorias();
        nuevaCategoria.setNombre(Nombre);
        nuevaCategoria.setDescripcion(Descripcion);

        int categoriaId = categoriasDao.addCategoria(nuevaCategoria);

        if (categoriaId != -1) {
            out.print("<p>Categoría agregada exitosamente con ID " + categoriaId + ".</p>");
        } else {
            out.print("<p>Error al agregar la categoría.</p>");
        }

        out.flush();
    }
}