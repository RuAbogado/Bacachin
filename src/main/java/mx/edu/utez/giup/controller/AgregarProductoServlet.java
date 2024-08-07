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
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir solicitudes de cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Encabezados permitidos

        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoria = Integer.parseInt(request.getParameter("ID_Categoria"));

        // Obtener y guardar la imagen
        Part filePart = request.getPart("imagen");
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
        Date Fecha_creacion = Date.valueOf(fechaCreacionString);

        // Crear el objeto Producto
        Productos producto = new Productos(0, categoria, nombre, descripcion, precio, stock, Fecha_creacion, "x", fileName);


        // Llamar al DAO para agregar el producto
        ProductosDao productosDao = new ProductosDao();
        boolean agregado = productosDao.agregarProducto(producto);

        // Devolver el HTML del nuevo producto
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (agregado) {
            out.println("<div class='producto'>");
            out.println("<h1>exitosamente</h1>");
            out.println("<img src='img/" + fileName + "' alt='" + nombre + "' />");
            out.println("<h3>" + nombre + "</h3>");
            out.println("<p>" + descripcion + "</p>");
            out.println("<p>Precio: $" + precio + "</p>");
            out.println("<p>Stock: " + stock + "</p>");
            out.println("</div>");
        } else {
            out.println("<p>Error al agregar el producto.</p>");
        }
        out.flush();
        }
    }
