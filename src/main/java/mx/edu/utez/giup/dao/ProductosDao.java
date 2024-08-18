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
        String query = "INSERT INTO Productos (ID_Categoria, Nombre, Descripcion, Precio, Stock, Fecha_creacion, ID_Marca, Imagen, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, producto.getID_Categoria());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setFloat(4, producto.getPrecio());  // Cambiado a float
            stmt.setInt(5, producto.getStock());
            stmt.setDate(6, producto.getFecha_Creacion());
            stmt.setInt(7, producto.getID_Marca());  // Cambiado a int ID_Marca
            stmt.setString(8, producto.getImagen());
            stmt.setBoolean(9, producto.getEstado());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los productos
    public List<Productos> obtenerTodosLosProductos() {
        List<Productos> productosList = new ArrayList<>();
        String query = "SELECT * FROM Productos WHERE Estado = true";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Productos producto = new Productos(
                        rs.getInt("ID_Producto"),
                        rs.getInt("ID_Categoria"),
                        rs.getString("Nombre"),
                        rs.getString("Descripcion"),
                        rs.getFloat("Precio"),  // Cambiado a float
                        rs.getInt("Stock"),
                        rs.getDate("Fecha_creacion"),
                        rs.getInt("ID_Marca"),  // Cambiado a int ID_Marca
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
        String query = "DELETE FROM Productos WHERE ID_Producto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProducto);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerIdPorNombre(String nombre) {
        String query = "SELECT ID_Producto FROM Productos WHERE Nombre = ? AND Estado = true";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_Producto");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra el producto
    }

}