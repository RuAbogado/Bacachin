package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;

public class CarritoDao {

    // Método generalizado para obtener un carrito por ID de usuario y tipo
    public Carrito getCarritoByUsuarioId(int userId, String userType) {
        Carrito carrito = null;
        String sql = "";

        switch (userType.toLowerCase()) {
            case "cliente":
                sql = "SELECT * FROM Carrito WHERE ID_Cliente = ?";
                break;
            case "empleado":
                sql = "SELECT * FROM Carrito_empleado WHERE ID_Empleado = ?";
                break;
            case "admin":
                sql = "SELECT * FROM Carrito_admin WHERE ID_Admin = ?";
                break;
            default:
                return null; // Tipo de usuario no reconocido
        }

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                carrito = new Carrito();
                carrito.setID_Carrito(resultSet.getInt(1)); // Asumiendo que el ID del carrito es la primera columna

                // Obtener el valor de Fecha_Creacion directamente como java.sql.Date
                carrito.setFecha_Creacion(resultSet.getDate("Fecha_Creacion"));

                // Configura los demás campos del carrito según necesites
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el carrito: " + e.getMessage());
        }
        return carrito;
    }

    public Carrito createCarrito(Carrito carrito) {
        String sql = "";
        // Elegir la tabla correcta en base al tipo de usuario
        switch (carrito.getUserType().toLowerCase()) {
            case "cliente":
                sql = "INSERT INTO Carrito (ID_Cliente, Fecha_Creacion) VALUES (?, ?)";
                break;
            case "empleado":
                sql = "INSERT INTO Carrito_empleado (ID_Empleado, Fecha_Creacion) VALUES (?, ?)";
                break;
            case "admin":
                sql = "INSERT INTO Carrito_admin (ID_Admin, Fecha_Creacion) VALUES (?, ?)";
                break;
            default:
                return null; // Tipo de usuario no reconocido
        }

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, carrito.getUserID());
            statement.setDate(2, new java.sql.Date(carrito.getFecha_Creacion().getTime()));

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