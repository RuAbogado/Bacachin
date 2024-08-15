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
        String query = "INSERT INTO Productos (ID_Categoria, Nombre, Descripcion, Precio, Stock, Fecha_creacion, Tipo, Imagen, Estado) VALUES ( ?, ?, ?, ?, ?, ?, 'x', ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, producto.getID_Categoria());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setFloat(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setDate(6, producto.getFecha_creacion());
            stmt.setString(7, producto.getImagen());
            stmt.setBoolean(8, producto.getEstado());  // Corregido índice aquí
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para cargar productos por categoría
    public List<Productos> cargarProductosPorCategoria(int categoriaID) {
        List<Productos> productosList = new ArrayList<>();
        String query = "{CALL GetProductosPorCategoria(?)}";

        try (CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setInt(1, categoriaID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Productos producto = new Productos(
                        rs.getInt("ID_Producto"),
                        rs.getInt("ID_Categoria"),
                        rs.getString("Nombre"),
                        rs.getString("Descripcion"),
                        rs.getFloat("Precio"),
                        rs.getInt("Stock"),
                        rs.getDate("Fecha_Creacion"),
                        rs.getString("Tipo"),
                        rs.getString("Imagen"),
                        rs.getBoolean("Estado")
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
