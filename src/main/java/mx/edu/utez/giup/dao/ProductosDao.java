package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Productos;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosDao {

    private Connection connection;

    public ProductosDao() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la conexión a la base de datos.", e);
        }
    }

    // Método para agregar un producto
    public boolean agregarProducto(Productos producto) {
        String query = "INSERT INTO productos (ID_Producto, ID_Categoria, Nombre, Descripcion, Precio, Stock, Fecha_creacion, Tipo, Imagen) VALUES (?, ?, ?, ?, ?, ?, ?, 'x', ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, producto.getID_Producto());
            stmt.setInt(2, producto.getID_Categoria());
            stmt.setString(3, producto.getNombre());
            stmt.setString(4, producto.getDescripcion());
            stmt.setDouble(5, producto.getPrecio());
            stmt.setInt(6, producto.getStock());
            stmt.setDate(7, producto.getFecha_creacion());
            stmt.setString(8, producto.getTipo());
            stmt.setString(9, producto.getImagen());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Método para cargar todos los productos
    public List<Productos> cargarProductos() {
        List<Productos> productosList = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Productos producto = new Productos(
                        rs.getInt("ID_Producto"),
                        rs.getInt("ID_categoria"),
                        rs.getString("Nombre"),
                        rs.getString("Descripcion"),
                        rs.getFloat("Precio"),
                        rs.getInt("Stock"),
                        rs.getString("Fecha_creacion"),
                        rs.getString("Tipo"),
                        rs.getString("Imagen")
                );
                productosList.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int idProducto) {
        String query = "DELETE FROM productos WHERE ID_Producto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProducto);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}