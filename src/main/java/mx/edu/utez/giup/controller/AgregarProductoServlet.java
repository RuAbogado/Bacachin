package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mx.edu.utez.giup.dao.ProductosDao;
import mx.edu.utez.giup.model.Productos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/AgregarProducto")
@MultipartConfig
public class AgregarProductoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Permitir solicitudes CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        try {
            // Obtener los parámetros del formulario
            String nombre = request.getParameter("Nombre");
            String descripcion = request.getParameter("Descripcion");
            float precio = Float.parseFloat(request.getParameter("Precio")); // Usar float
            int stock = Integer.parseInt(request.getParameter("Stock"));
            int categoria = Integer.parseInt(request.getParameter("ID_Categoria"));
            int marca = Integer.parseInt(request.getParameter("ID_Marca")); // Obtener el ID de la marca
            boolean estado = Boolean.parseBoolean(request.getParameter("Estado"));

            // Obtener y guardar la imagen
            Part filePart = request.getPart("Imagen");
            String fileName = filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "img";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePart.write(uploadPath + File.separator + fileName);

            // Crear la fecha actual en formato "yyyy-MM-dd"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String fechaCreacionString = formatter.format(new java.util.Date());
            Date fechaCreacion = Date.valueOf(fechaCreacionString);

            // Crear el objeto Producto
            Productos producto = new Productos(0, categoria, nombre, descripcion, precio, stock, fechaCreacion, marca, fileName, estado);

            // Llamar al DAO para agregar el producto
            ProductosDao productosDao = new ProductosDao();
            boolean agregado = productosDao.agregarProducto(producto);

            // Devolver el HTML del nuevo producto
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (agregado) {
                out.println("<div class='producto'>");
                out.println("<h1>Producto agregado exitosamente</h1>");
                out.println("<img src='img/" + fileName + "' alt='" + nombre + "' />");
                out.println("<h3>" + nombre + "</h3>");
                out.println("<p>" + descripcion + "</p>");
                out.println("<p>Precio: $" + String.format("%.2f", precio) + "</p>"); // Formatear a dos decimales
                out.println("<p>Stock: " + stock + "</p>");
                out.println("<p>Estado: " + (estado ? "Activo" : "Inactivo") + "</p>");
                out.println("</div>");
            } else {
                out.println("<p>Error al agregar el producto.</p>");
            }
            out.flush();

        } catch (NumberFormatException e) {
            // Manejar posibles errores de conversión numérica
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de número inválido.");
        } catch (Exception e) {
            // Manejar cualquier otra excepción
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
            e.printStackTrace();
        }
    }
}