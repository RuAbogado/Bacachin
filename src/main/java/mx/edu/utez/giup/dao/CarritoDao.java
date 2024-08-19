package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;

public class CarritoDao {

    // Método para obtener un carrito por el ID del cliente
    public Carrito getCarritoByClienteId(int clienteId) {
        Carrito carrito = null;
        String sql = "SELECT * FROM Carrito WHERE ID_Cliente = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                carrito = new Carrito();
                carrito.setID_Carrito(resultSet.getInt("ID_Carrito"));
                carrito.setID_Cliente(resultSet.getInt("ID_Cliente"));
                carrito.setFecha_Creacion(resultSet.getDate("Fecha_Creacion"));
                carrito.setCantidad(resultSet.getInt("Cantidad"));
                // Aquí podrías cargar los productos asociados al carrito si es necesario
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el carrito: " + e.getMessage());
        }
        return carrito;
    }

    // Método para crear un nuevo carrito
    public Carrito createCarrito(Carrito carrito) {
        String sql = "INSERT INTO Carrito (ID_Cliente, Fecha_Creacion) VALUES (?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, carrito.getID_Cliente());
            statement.setDate(2, new java.sql.Date(carrito.getFecha_Creacion().getTime())); // Convierte java.util.Date a java.sql.Date

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    carrito.setID_Carrito(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el carrito: " + e.getMessage());
        }
        return carrito;
    }

    // Método para actualizar un carrito
    public boolean updateCarrito(Carrito carrito) {
        boolean updated = false;
        String sql = "UPDATE Carrito SET Fecha_Creacion = ?, Cantidad = ? WHERE ID_Carrito = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(carrito.getFecha_Creacion().getTime())); // Convierte java.util.Date a java.sql.Date
            statement.setInt(2, carrito.getCantidad());
            statement.setInt(3, carrito.getID_Carrito());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el carrito: " + e.getMessage());
        }
        return updated;
    }

    // Método para eliminar un carrito por su ID
    public boolean deleteCarrito(int carritoId) {
        boolean deleted = false;
        String sql = "DELETE FROM Carrito WHERE ID_Carrito = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, carritoId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el carrito: " + e.getMessage());
        }
        return deleted;
    }
}