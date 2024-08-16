package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.CategoriasDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deshabilitarCategoria")
public class deshabilitarCategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoriasDao categoriasDao = new CategoriasDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // Obtener el ID de la categoría desde los parámetros de la solicitud
        String ID_CategoriaParam = request.getParameter("ID_Categoria");
        System.out.println(ID_CategoriaParam);

        if (ID_CategoriaParam == null || ID_CategoriaParam.isEmpty()) {
            out.print("{\"success\":false, \"message\":\"Category ID is missing.\"}");
            out.flush();
            return;
        }

        try {
            int ID_Categoria = Integer.parseInt(ID_CategoriaParam);

            // Eliminar la categoría usando el DAO
            boolean success = categoriasDao.deshabilitarCategoria(ID_Categoria);

            if (success) {
                out.print("{\"success\":true}");
            } else {
                out.print("{\"success\":false}");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.print("{\"success\":false, \"message\":\"Invalid category ID.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\":false, \"message\":\"Error deleting category.\"}");
        }

        out.flush();
    }
}