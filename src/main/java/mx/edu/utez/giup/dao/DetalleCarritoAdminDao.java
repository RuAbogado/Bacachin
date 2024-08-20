package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleCarritoAdmin;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoAdminDao {

    // Método para obtener los detalles del carrito de admin por ID de carrito
    public List<DetalleCarritoAdmin> getDetallesByCarritoId(int idCarritoAdmin) {
        List<DetalleCarritoAdmin> detalles = new ArrayList<>();
        String sql = "SELECT dc.*, p.Nombre, p.Precio FROM Detalle_carrito_admin dc JOIN Productos p ON dc.ID_Producto = p.ID_Producto WHERE dc.ID_Carrito_admin = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCarritoAdmin);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleCarritoAdmin detalle = new DetalleCarritoAdmin();
                detalle.setIdDetalleCarritoAdmin(resultSet.getInt("ID_DetalleCarritoAdmin"));
                detalle.setIdCarritoAdmin(resultSet.getInt("ID_Carrito_admin"));
                detalle.setIdProducto(resultSet.getInt("ID_Producto"));
                detalle.setCantidad(resultSet.getInt("Cantidad"));

                // Setear los detalles del producto
                detalle.setNombreProducto(resultSet.getString("Nombre"));
                detalle.setPrecioProducto(resultSet.getFloat("Precio"));

                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los detalles del carrito de admin: " + e.getMessage());
        }

        return detalles;
    }

    // Método para agregar o actualizar un detalle del carrito de admin
    public void agregarOActualizarDetalleCarrito(DetalleCarritoAdmin detalleCarritoAdmin) {
        String sqlInsert = "INSERT INTO Detalle_carrito_admin (ID_Carrito_admin, ID_Producto, Cantidad) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE Detalle_carrito_admin SET Cantidad = ? WHERE ID_Carrito_admin = ? AND ID_Producto = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Intentar actualizar primero
            try (PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate)) {
                statementUpdate.setInt(1, detalleCarritoAdmin.getCantidad());
                statementUpdate.setInt(2, detalleCarritoAdmin.getIdCarritoAdmin());
                statementUpdate.setInt(3, detalleCarritoAdmin.getIdProducto());

                int rowsUpdated = statementUpdate.executeUpdate();

                // Si no se actualizó ninguna fila, intentar insertar
                if (rowsUpdated == 0) {
                    try (PreparedStatement statementInsert = connection.prepareStatement(sqlInsert)) {
                        statementInsert.setInt(1, detalleCarritoAdmin.getIdCarritoAdmin());
                        statementInsert.setInt(2, detalleCarritoAdmin.getIdProducto());
                        statementInsert.setInt(3, detalleCarritoAdmin.getCantidad());

                        statementInsert.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al agregar o actualizar detalle del carrito de admin: " + e.getMessage());
        }
    }
}
