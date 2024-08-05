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
import java.util.List;

@WebServlet("/ObtenerCategorias")
public class ObtenerCategoriasServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar encabezados CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permite solicitudes desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Encabezados permitidosgit

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        CategoriasDao categoriasDao = new CategoriasDao();
        List<Categorias> categorias = categoriasDao.getAllCategorias();

        // Generar HTML para las categorías
        PrintWriter out = response.getWriter();
        //out.println("<ul>");
        for (Categorias categoria : categorias) {
            out.println("<option value=\"" + categoria.getID_Categoria() +"\">" + categoria.getNombre() + "</option>");
        }
        //out.println("</ul>");
    }
}