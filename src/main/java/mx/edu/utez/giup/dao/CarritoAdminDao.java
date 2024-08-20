package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.CarritoAdmin;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;

public class CarritoAdminDao {

    // Método para obtener un carrito por el ID del administrador
    public CarritoAdmin getCarritoByAdminId(int adminId) {
        CarritoAdmin carrito = null;
        String sql = "SELECT * FROM Carrito_admin WHERE ID_Admin = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                carrito = new CarritoAdmin();
                carrito.setID_Carrito_admin(resultSet.getInt("ID_Carrito_admin"));
                carrito.setID_Admin(resultSet.getInt("ID_Admin"));
                carrito.setFecha_Creacion(resultSet.getDate("Fecha_Creacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el carrito del administrador: " + e.getMessage());
        }
        return carrito;
    }

    // Método para crear un nuevo carrito para un administrador
    public CarritoAdmin createCarrito(CarritoAdmin carrito) {
        String sql = "INSERT INTO Carrito_admin (ID_Admin, Fecha_Creacion) VALUES (?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, carrito.getID_Admin());
            statement.setDate(2, new java.sql.Date(carrito.getFecha_Creacion().getTime()));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    carrito.setID_Carrito_admin(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el carrito del administrador: " + e.getMessage());
        }
        return carrito;
    }

    // Método para actualizar un carrito de administrador
    public boolean updateCarrito(CarritoAdmin carrito) {
        boolean updated = false;
        String sql = "UPDATE Carrito_admin SET Fecha_Creacion = ? WHERE ID_Carrito_admin = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(carrito.getFecha_Creacion().getTime()));
            statement.setInt(2, carrito.getID_Carrito_admin());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el carrito del administrador: " + e.getMessage());
        }
        return updated;
    }

    // Método para eliminar un carrito de administrador por su ID
    public boolean deleteCarrito(int carritoAdminId) {
        boolean deleted = false;
        String sql = "DELETE FROM Carrito_admin WHERE ID_Carrito_admin = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, carritoAdminId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el carrito del administrador: " + e.getMessage());
        }
        return deleted;
    }


    // Método para eliminar un producto específico del carrito del administrador
    public boolean eliminarProductoDelCarrito(int idCarritoAdmin, int idProducto) {
        boolean success = false;
        String sql = "DELETE FROM Detalle_carrito_admin WHERE ID_Carrito_admin = ? AND ID_Producto = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCarritoAdmin);
            statement.setInt(2, idProducto);

            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto del carrito del administrador: " + e.getMessage());
        }

        return success;
    }
}