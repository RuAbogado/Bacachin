package mx.edu.utez.giup.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.giup.dao.DetalleCarritoAdminDao;
import mx.edu.utez.giup.model.DetalleCarritoAdmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AgregarProductoCarritoAdmin")
public class AgregarProductoCarritoAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DetalleCarritoAdminDao detalleCarritoAdminDao = new DetalleCarritoAdminDao();

    public AgregarProductoCarritoAdminServlet() throws SQLException {
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

            // Parsear JSON para obtener los datos
            JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
            int idProducto = jsonObject.get("idProducto").getAsInt();
            int cantidad = jsonObject.get("quantity").getAsInt();

            // Obtener el ID del carrito de admin desde la sesión
            HttpSession session = request.getSession();
            Integer idCarritoAdmin = (Integer) session.getAttribute("ID_CarritoAdmin");

            if (idCarritoAdmin == null) {
                out.write("{\"status\":\"error\", \"message\":\"ID_CarritoAdmin no está en la sesión\"}");
                return;
            }

            // Verificar si el producto ya está en el carrito
            DetalleCarritoAdmin detalleExistente = detalleCarritoAdminDao.obtenerDetallePorCarritoYProducto(idCarritoAdmin, idProducto);

            if (detalleExistente != null) {
                // Si el producto ya está en el carrito, actualizar la cantidad
                int nuevaCantidad = detalleExistente.getCantidad() + cantidad;
                detalleCarritoAdminDao.actualizarCantidad(idCarritoAdmin, idProducto, nuevaCantidad);
            } else {
                // Si el producto no está en el carrito, insertar un nuevo detalle
                DetalleCarritoAdmin detalleCarritoAdmin = new DetalleCarritoAdmin(idCarritoAdmin, idProducto, cantidad);
                detalleCarritoAdminDao.insertarDetalle(detalleCarritoAdmin);
            }

            // Respuesta de éxito
            out.write("{\"status\":\"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}