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
    private static final long serialVersionUID = 1L;

    private ProductosDao productosDao = new ProductosDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Obtener los parámetros del formulario
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoria = Integer.parseInt(request.getParameter("ID_Categoria"));
            int marca = Integer.parseInt(request.getParameter("ID_Marca"));
            boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

            // Validar si los parámetros son nulos o vacíos
            if (nombre == null || nombre.isEmpty() || descripcion == null || descripcion.isEmpty()) {
                throw new IllegalArgumentException("Nombre o Descripción están vacíos");
            }

            // Obtener y guardar la imagen
            Part filePart = request.getPart("imagen");
            if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
                throw new IllegalArgumentException("El archivo de imagen es requerido");
            }
            String fileName = filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("img");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePart.write(uploadPath + File.separator + fileName);

            // Crear la fecha actual en formato "yyyy-MM-dd"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String fechaCreacionString = formatter.format(new java.util.Date());
            Date fechaCreacion = Date.valueOf(fechaCreacionString);

            Productos nuevoProducto = new Productos();
            nuevoProducto.setID_Categoria(categoria);
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setStock(stock);
            nuevoProducto.setFecha_Creacion(fechaCreacion);
            nuevoProducto.setID_Marca(marca);
            nuevoProducto.setImagen(fileName);
            nuevoProducto.setEstado(estado);

            // Guardar el producto y obtener el ID generado
            int productoId = productosDao.agregarProductoYObtenerID(nuevoProducto);

            if (productoId > 0) {
                out.print("{\"status\":\"success\", \"message\":\"Producto agregado exitosamente\", \"productId\":" + productoId + "}");
            } else {
                out.print("{\"status\":\"error\", \"message\":\"Error al agregar el producto\"}");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostrar el error en el log
            out.print("{\"status\":\"error\", \"message\":\"Error procesando el formulario: " + e.getMessage() + "\"}");
        }

        out.flush();
    }
}