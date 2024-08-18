package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.MarcasDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deshabilitarMarca")
public class deshabilitarMarcaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MarcasDao marcasDao = new MarcasDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // Obtener el ID de la marca desde los parámetros de la solicitud
        String ID_MarcaParam = request.getParameter("ID_Marcas");
        System.out.println(ID_MarcaParam);

        if (ID_MarcaParam == null || ID_MarcaParam.isEmpty()) {
            out.print("{\"success\":false, \"message\":\"Marca ID is missing.\"}");
            out.flush();
            return;
        }

        try {
            int ID_Marca = Integer.parseInt(ID_MarcaParam);

            // Eliminar la marca usando el DAO
            boolean success = marcasDao.deshabilitarMarcas(ID_Marca);

            if (success) {
                out.print("{\"success\":true}");
            } else {
                out.print("{\"success\":false}");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.print("{\"success\":false, \"message\":\"Invalid marca ID.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\":false, \"message\":\"Error deleting marca.\"}");
        }

        out.flush();
    }
}