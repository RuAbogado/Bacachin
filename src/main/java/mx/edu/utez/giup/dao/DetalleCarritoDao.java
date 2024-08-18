package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleCarrito;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoDao {

    public void agregarDetalleCarrito(DetalleCarrito detalleCarrito) throws SQLException {
        String sql = "INSERT INTO Detalle_carrito (ID_Carrito, ID_Producto, Cantidad) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalleCarrito.getIdCarrito());
            statement.setInt(2, detalleCarrito.getIdProducto());
            statement.setInt(3, detalleCarrito.getCantidad());

            statement.executeUpdate();
        }
    }

    public List<DetalleCarrito> getDetallesByCarritoId(int carritoId) {
        List<DetalleCarrito> detalles = new ArrayList<>();
        String sql = "SELECT * FROM Detalle_carrito WHERE ID_Carrito = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, carritoId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleCarrito detalleCarrito = new DetalleCarrito(
                        resultSet.getInt("ID_Carrito"),
                        resultSet.getInt("ID_Producto"),
                        resultSet.getInt("Cantidad")
                );
                detalleCarrito.setIdDetalleCarrito(resultSet.getInt("ID_DetalleCarrito")); // Asigna el ID
                detalles.add(detalleCarrito);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public boolean deleteDetalleByCarritoId(int carritoId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM Detalle_carrito WHERE ID_Carrito = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, carritoId);
            int rowsAffected = statement.executeUpdate();
            isDeleted = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public boolean deleteDetalleByProductoId(int carritoId, int productoId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM Detalle_carrito WHERE ID_Carrito = ? AND ID_Producto = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, carritoId);
            statement.setInt(2, productoId);
            int rowsAffected = statement.executeUpdate();
            isDeleted = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}