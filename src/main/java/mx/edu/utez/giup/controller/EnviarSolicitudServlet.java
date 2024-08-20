package mx.edu.utez.giup.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.DetalleSolicitudDao;
import mx.edu.utez.giup.dao.SolicitudesDao;
import mx.edu.utez.giup.model.DetalleSolicitud;
import mx.edu.utez.giup.model.Solicitudes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/EnviarSolicitud")
public class EnviarSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = null;

        try {
            out = resp.getWriter();

            // Leer el JSON del cuerpo de la solicitud
            BufferedReader reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String jsonData = jsonBuilder.toString();
            Gson gson = new Gson();
            SolicitudRequest solicitudRequest = gson.fromJson(jsonData, SolicitudRequest.class);

            // Validar datos de la solicitud
            if (solicitudRequest.getID_Cliente() <= 0 || solicitudRequest.getProductos().isEmpty()) {
                throw new IllegalArgumentException("Datos de solicitud incompletos o inválidos.");
            }

            // Crear solicitud en la base de datos
            SolicitudesDao solicitudesDao = new SolicitudesDao();
            Solicitudes solicitud = new Solicitudes();
            solicitud.setID_Cliente(solicitudRequest.getID_Cliente());
            solicitud.setFecha_Solicitud(LocalDate.now());
            solicitud.setEstado("Pendiente");

            int solicitudId = solicitudesDao.createSolicitud(solicitud);
            if (solicitudId > 0) {
                DetalleSolicitudDao detalleSolicitudDao = new DetalleSolicitudDao();
                for (ProductoRequest producto : solicitudRequest.getProductos()) {
                    DetalleSolicitud detalle = new DetalleSolicitud();
                    detalle.setID_Solicitud(solicitudId);
                    detalle.setID_Producto(producto.getID_Producto());
                    detalle.setCantidad(producto.getCantidad());
                    detalle.setPrecio(producto.getPrecio());

                    boolean success = detalleSolicitudDao.createDetalleSolicitud(detalle);
                    if (!success) {
                        throw new Exception("Error al insertar detalles de solicitud.");
                    }
                }

                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"success\": true}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"success\": false, \"message\": \"Error al crear la solicitud\"}");
            }

        } catch (JsonSyntaxException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"success\": false, \"message\": \"Error en el formato de JSON: " + e.getMessage() + "\"}");
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"success\": false, \"message\": \"Error interno del servidor: " + e.getMessage() + "\"}");
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    // Clases internas para deserializar el JSON
    private class SolicitudRequest {
        private int ID_Cliente;
        private List<ProductoRequest> productos;

        public int getID_Cliente() {
            return ID_Cliente;
        }

        public List<ProductoRequest> getProductos() {
            return productos;
        }
    }

    private class ProductoRequest {
        private int ID_Producto;
        private int Cantidad;
        private float Precio;

        public int getID_Producto() {
            return ID_Producto;
        }

        public int getCantidad() {
            return Cantidad;
        }

        public float getPrecio() {
            return Precio;
        }
    }
}