package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleCarritoEmpleado;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoEmpleadoDao {

    // Método para obtener los detalles del carrito de empleado por ID de carrito
    public List<DetalleCarritoEmpleado> getDetallesByCarritoId(int idCarritoEmpleado) {
        List<DetalleCarritoEmpleado> detalles = new ArrayList<>();
        String sql = "SELECT dc.*, p.Nombre, p.Precio FROM Detalle_carrito_empleado dc JOIN Productos p ON dc.ID_Producto = p.ID_Producto WHERE dc.ID_Carrito_empleado = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCarritoEmpleado);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleCarritoEmpleado detalle = new DetalleCarritoEmpleado();
                detalle.setIdDetalleCarritoEmpleado(resultSet.getInt("ID_DetalleCarritoEmpleado"));
                detalle.setIdCarritoEmpleado(resultSet.getInt("ID_Carrito_empleado"));
                detalle.setIdProducto(resultSet.getInt("ID_Producto"));
                detalle.setCantidad(resultSet.getInt("Cantidad"));

                // Setear los detalles del producto
                detalle.setNombreProducto(resultSet.getString("Nombre"));
                detalle.setPrecioProducto(resultSet.getFloat("Precio"));

                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los detalles del carrito de empleado: " + e.getMessage());
        }

        return detalles;
    }

    // Método para agregar o actualizar un detalle en el carrito de empleado
    public void agregarOActualizarDetalleCarrito(DetalleCarritoEmpleado detalleCarritoEmpleado) throws SQLException {
        String sqlSelect = "SELECT Cantidad FROM Detalle_carrito_empleado WHERE ID_Carrito_empleado = ? AND ID_Producto = ?";
        String sqlUpdate = "UPDATE Detalle_carrito_empleado SET Cantidad = ? WHERE ID_Carrito_empleado = ? AND ID_Producto = ?";
        String sqlInsert = "INSERT INTO Detalle_carrito_empleado (ID_Carrito_empleado, ID_Producto, Cantidad) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // Primero, verifica si ya existe un detalle para el producto en el carrito
            try (PreparedStatement selectStmt = connection.prepareStatement(sqlSelect)) {
                selectStmt.setInt(1, detalleCarritoEmpleado.getIdCarritoEmpleado());
                selectStmt.setInt(2, detalleCarritoEmpleado.getIdProducto());
                ResultSet resultSet = selectStmt.executeQuery();

                if (resultSet.next()) {
                    // Si el detalle ya existe, actualiza la cantidad
                    int nuevaCantidad = resultSet.getInt("Cantidad") + detalleCarritoEmpleado.getCantidad();

                    try (PreparedStatement updateStmt = connection.prepareStatement(sqlUpdate)) {
                        updateStmt.setInt(1, nuevaCantidad);
                        updateStmt.setInt(2, detalleCarritoEmpleado.getIdCarritoEmpleado());
                        updateStmt.setInt(3, detalleCarritoEmpleado.getIdProducto());
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Si el detalle no existe, inserta un nuevo registro
                    try (PreparedStatement insertStmt = connection.prepareStatement(sqlInsert)) {
                        insertStmt.setInt(1, detalleCarritoEmpleado.getIdCarritoEmpleado());
                        insertStmt.setInt(2, detalleCarritoEmpleado.getIdProducto());
                        insertStmt.setInt(3, detalleCarritoEmpleado.getCantidad());
                        insertStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar o actualizar el detalle del carrito de empleado: " + e.getMessage());
        }
    }
}