package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mx.edu.utez.giup.dao.ProductosDao;
import mx.edu.utez.giup.model.Productos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.UUID;

@WebServlet("/AgregarProducto")
public class AgregarProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Obtener parámetros del formulario
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");

            float precio;
            try {
                String precioStr = request.getParameter("precio");
                if (precioStr != null && !precioStr.trim().isEmpty()) {
                    precio = Float.parseFloat(precioStr.trim());
                } else {
                    throw new NumberFormatException("Precio no proporcionado");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Precio inválido");
                return;
            }

            int stock=0;
            try {
                String stockStr = request.getParameter("stock");
                if (stockStr != null && !stockStr.trim().isEmpty()) {
                    stock = Integer.parseInt(stockStr.trim());
                } else {
                    throw new NumberFormatException("Stock no proporcionado");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Stock inválido");
                return;
            }

            int idCategoria;
            try {
                String idCategoriaStr = request.getParameter("ID_Categoria");
                if (idCategoriaStr != null && !idCategoriaStr.trim().isEmpty()) {
                    idCategoria = Integer.parseInt(idCategoriaStr.trim());
                } else {
                    throw new NumberFormatException("ID_Categoria no proporcionado");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID_Categoria inválido");
                return;
            }

            String tipo = request.getParameter("Tipo");

            // Crear una instancia de Productos con los datos proporcionados
            Productos producto = new Productos(
                    0, // Asignar un valor inicial para ID_Producto
                    idCategoria,
                    nombre,
                    descripcion,
                    precio,
                    stock,
                    new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()), // Fecha actual en formato String
                    tipo,
                    "" // Imagen vacía, se actualizará más tarde
            );

            // Definición de la carpeta de carga de imágenes
            String UPLOAD_DIRECTORY = getServletContext().getRealPath("/") + "img";
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                if (!uploadDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + UPLOAD_DIRECTORY);
                }
            }

            // Manejar la carga de archivos
            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getSubmittedFileName(filePart);
                String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                String filePath = UPLOAD_DIRECTORY + File.separator + uniqueFileName;

                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, Paths.get(filePath));
                }
                producto.setImagen("img/" + uniqueFileName); // Ajustar la ruta de la imagen
            } else {
                throw new ServletException("No file uploaded or file is empty");
            }

            // Insertar producto en la base de datos
            ProductosDao productosDao = new ProductosDao();
            boolean success = productosDao.agregarProducto(producto);

            // Responder según el resultado
            if (success) {
                out.println("<html><body>");
                out.println("<h1>Producto agregado exitosamente</h1>");
                out.println("<a href='productos.jsp'>Volver a la lista de productos</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h1>Error al agregar el producto</h1>");
                out.println("<a href='productos.jsp'>Volver a intentar</a>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace(out); // Esto imprimirá la excepción en la respuesta para diagnóstico
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            out.flush();
        }
    }

    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("content-disposition");
        String[] elements = header.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
}