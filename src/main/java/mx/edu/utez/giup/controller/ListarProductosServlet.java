package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ListarProductos")
public class ListarProductosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            String sql = "SELECT Productos.ID_Producto, Productos.ID_Categoria, Productos.Nombre, Productos.Descripcion, Productos.Precio, Productos.Stock, Productos.Fecha_Creacion, Productos.ID_Marca, Productos.Estado, Productos.Imagen, Categorias.ID_Categoria AS Categoria_ID, Categorias.Nombre AS Categoria_Nombre, Categorias.Estado AS Categorias_Estado FROM Productos JOIN Categorias ON Productos.ID_Categoria = Categorias.ID_Categoria";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            out.println("<table id='clientTable'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Categoria</th>");
            out.println("<th>Imagen</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Descripcion</th>");
            out.println("<th>Precio</th>");
            out.println("<th>Stock</th>");
            out.println("<th>Fecha de creacion</th>");
            out.println("<th>Marca</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Modificar Estado");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (rs.next()) {
                if(rs.getString("Nombre") != null){

                    out.println("<tr>");
                    //nombre de la categoria
                    out.println("<td>" + rs.getString("Categoria_Nombre") + "</td>");
                    String Imagen = rs.getString("Imagen");
                    out.println("<td><img style=\"width:50px\" alt=\"imagenproducto\" src=\"img/"+Imagen+"\"></td>");
                    out.println("<td>" + rs.getString("Nombre") + "</td>");
                    out.println("<td>" + rs.getString("Descripcion") + "</td>");
                    out.println("<td>" + rs.getString("Precio") + "</td>");
                    out.println("<td>" + rs.getString("Stock") + "</td>");
                    out.println("<td>" + rs.getString("Fecha_Creacion") + "</td>");
                    out.println("<td>" + rs.getString("ID_Marca") + "</td>");
                    //estado del producto
                    int Categorias_Estado = rs.getInt("Categorias_Estado");
                    int estado = rs.getInt("Estado");
                    if (estado == 1 && Categorias_Estado == 1) {
                        out.println("<td>Habilitada</td>");
                    }else if (estado == 0 && Categorias_Estado == 1) {
                        out.println("<td>Deshabilitada</td>");
                    }else if (Categorias_Estado == 0) {
                        out.println("<td>Categoria Deshabilitada</td>");
                    }else {
                        out.println("<td>error en la solicitud</td>");
                    }
                    int Categorias_id = rs.getInt("Categoria_id");
                    int ID_Producto = rs.getInt("ID_Producto");
                    if (estado == 1 && Categorias_Estado == 1) {
                        out.println("<td><button type=\"button\" onclick=\"DeshabilitarProducto(" + ID_Producto + ")\">Deshabilitar Producto</button></td>");
                    }else if (estado == 0 && Categorias_Estado == 1) {
                        out.println("<td><button type=\"button\" onclick=\"HabilitarProducto(" + ID_Producto + ")\">Habilitar Producto</button></td>");
                    }else if (Categorias_Estado==0){
                        out.println("<td><button type=\"button\" onclick=\"HabilitarCategoria(" + Categorias_id + ")\">Habilitar Categoria</button></td>");
                    }else {
                        out.println("<td>error en la solicitud</td>");
                    }

                    out.println("</tr>");
                }
            }
            out.println("</tbody>");
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}