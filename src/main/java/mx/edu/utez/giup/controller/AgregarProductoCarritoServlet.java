package mx.edu.utez.giup.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.DetalleCarritoDao;
import mx.edu.utez.giup.model.DetalleCarrito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AgregarProductoCarrito")
public class AgregarProductoCarritoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DetalleCarritoDao detalleCarritoDao = new DetalleCarritoDao();

    public AgregarProductoCarritoServlet() throws SQLException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Leer el cuerpo de la solicitud
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            // Parsear JSON con Gson
            JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
            int idProducto = jsonObject.get("idProducto").getAsInt();
            int cantidad = jsonObject.get("quantity").getAsInt();

            HttpSession session = request.getSession();
            Integer idCarrito = (Integer) session.getAttribute("ID_Carrito");

            if (idCarrito == null) {
                out.write("{\"status\":\"error\", \"message\":\"ID_Carrito no está en la sesión\"}");
                return;
            }

            // Crear el objeto DetalleCarrito
            DetalleCarrito detalleCarrito = new DetalleCarrito(idCarrito, idProducto, cantidad);

            // Usar DAO para insertar en la base de datos
            detalleCarritoDao.agregarDetalleCarrito(detalleCarrito);

            out.write("{\"status\":\"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}