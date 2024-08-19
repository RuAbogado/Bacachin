package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleCarrito;
import mx.edu.utez.giup.model.Productos;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoDao {

    // Método para agregar o actualizar un producto en el carrito
    public void agregarOActualizarDetalleCarrito(DetalleCarrito detalleCarrito) throws SQLException {
        String sqlSelect = "SELECT Cantidad FROM Detalle_carrito WHERE ID_Carrito = ? AND ID_Producto = ?";
        String sqlUpdate = "UPDATE Detalle_carrito SET Cantidad = Cantidad + ? WHERE ID_Carrito = ? AND ID_Producto = ?";
        String sqlInsert = "INSERT INTO Detalle_carrito (ID_Carrito, ID_Producto, Cantidad) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Verificar si ya existe un registro con el mismo ID_Carrito y ID_Producto
            try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {
                selectStatement.setInt(1, detalleCarrito.getIdCarrito());
                selectStatement.setInt(2, detalleCarrito.getIdProducto());

                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    // Si existe, actualizar la cantidad
                    try (PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate)) {
                        updateStatement.setInt(1, detalleCarrito.getCantidad());
                        updateStatement.setInt(2, detalleCarrito.getIdCarrito());
                        updateStatement.setInt(3, detalleCarrito.getIdProducto());
                        updateStatement.executeUpdate();
                    }
                } else {
                    // Si no existe, insertar un nuevo registro
                    try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {
                        insertStatement.setInt(1, detalleCarrito.getIdCarrito());
                        insertStatement.setInt(2, detalleCarrito.getIdProducto());
                        insertStatement.setInt(3, detalleCarrito.getCantidad());
                        insertStatement.executeUpdate();
                    }
                }
            }
        }
    }

    public List<DetalleCarrito> getDetallesByCarritoId(int idCarrito) {
        List<DetalleCarrito> detalles = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Consulta SQL actualizada
            String sql = "SELECT dc.ID_DetalleCarrito, dc.ID_Carrito, dc.ID_Producto, dc.Cantidad, p.Nombre, p.Precio " +
                    "FROM Detalle_carrito dc " +
                    "JOIN Productos p ON dc.ID_Producto = p.ID_Producto " +
                    "WHERE dc.ID_Carrito = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCarrito);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleCarrito detalle = new DetalleCarrito();
                detalle.setIdDetalleCarrito(resultSet.getInt("ID_DetalleCarrito"));
                detalle.setIdCarrito(resultSet.getInt("ID_Carrito"));
                detalle.setIdProducto(resultSet.getInt("ID_Producto"));
                detalle.setCantidad(resultSet.getInt("Cantidad"));

                // Crear y setear el objeto Producto
                Productos producto = new Productos();
                producto.setNombre(resultSet.getString("Nombre"));
                producto.setPrecio(resultSet.getFloat("Precio"));

                // Asignar el producto al detalle
                detalle.setProducto(producto);

                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

    // Método para eliminar todos los detalles del carrito por ID_Carrito
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

    // Método para eliminar un detalle del carrito por ID_Carrito e ID_Producto
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