package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.MarcasDao;
import mx.edu.utez.giup.model.Marcas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ObtenerMarcas")
public class ObtenerMarcasServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agregar encabezados CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permite solicitudes desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Encabezados permitidosgit

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        MarcasDao marcasDao = new MarcasDao();
        List<Marcas> marcass = marcasDao.getAllMarcas();

        // Generar HTML para las marcas
        PrintWriter out = response.getWriter();
        //out.println("<ul>");
        for (Marcas marcas : marcass) {
            out.println("<option value=\"" + marcas.getID_Marcas() +"\">" + marcas.getNombre() + "</option>");
        }
        //out.println("</ul>");
    }
}