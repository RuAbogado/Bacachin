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
import mx.edu.utez.giup.dao.DetalleCarritoEmpleadoDao;
import mx.edu.utez.giup.model.DetalleCarrito;
import mx.edu.utez.giup.model.DetalleCarritoEmpleado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/AgregarProductoCarritoEmpleado")
public class AgregarProductoCarritoEmpleadoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DetalleCarritoEmpleadoDao detalleCarritoEmpleadoDao = new DetalleCarritoEmpleadoDao();

    public AgregarProductoCarritoEmpleadoServlet() throws SQLException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
            int idProducto = jsonObject.get("idProducto").getAsInt();
            int cantidad = jsonObject.get("quantity").getAsInt();

            HttpSession session = request.getSession();
            Integer idCarritoEmpleado = (Integer) session.getAttribute("ID_CarritoEmpleado");

            if (idCarritoEmpleado == null) {
                out.write("{\"status\":\"error\", \"message\":\"ID_CarritoEmpleado no está en la sesión\"}");
                return;
            }

            DetalleCarritoEmpleado detalleCarritoEmpleado = new DetalleCarritoEmpleado(idCarritoEmpleado, idProducto, cantidad);
            detalleCarritoEmpleadoDao.agregarOActualizarDetalleCarrito(detalleCarritoEmpleado);

            out.write("{\"status\":\"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}