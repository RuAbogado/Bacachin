package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.dao.ProductosDao;
import mx.edu.utez.giup.model.Productos;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AgregarProducto")
public class AgregarProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idCategoria = Integer.parseInt(request.getParameter("ID_Categoria")); // Usar ID_Categoria aquí
        String tipo = request.getParameter("tipo");
        String imagen = request.getParameter("imagen");

        Productos producto = new Productos();
        producto.setID_Categoria(idCategoria);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio((int)precio);
        producto.setStock(stock);
        producto.setTipo(tipo);
        producto.setImagen(imagen);

        ProductosDao productosDao = new ProductosDao();

        boolean success = productosDao.agregarProducto(producto);

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

        out.flush();
    }
}