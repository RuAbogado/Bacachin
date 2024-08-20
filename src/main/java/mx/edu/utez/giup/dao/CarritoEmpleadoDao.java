package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.CarritoEmpleado;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;

public class CarritoEmpleadoDao {

    public CarritoEmpleado getCarritoByEmpleadoId(int empleadoId) {
        CarritoEmpleado carrito = null;
        String sql = "SELECT * FROM Carrito_empleado WHERE ID_Empleado = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, empleadoId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                carrito = new CarritoEmpleado();
                carrito.setID_Carrito_empleado(resultSet.getInt("ID_Carrito_empleado"));
                carrito.setID_Empleado(resultSet.getInt("ID_Empleado"));
                carrito.setFecha_Creacion(resultSet.getDate("Fecha_Creacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el carrito del empleado: " + e.getMessage());
        }
        return carrito;
    }

    public CarritoEmpleado createCarrito(CarritoEmpleado carrito) {
        String sql = "INSERT INTO Carrito_empleado (ID_Empleado, Fecha_Creacion) VALUES (?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, carrito.getID_Empleado());
            statement.setDate(2, new java.sql.Date(carrito.getFecha_Creacion().getTime()));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    carrito.setID_Carrito_empleado(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el carrito del empleado: " + e.getMessage());
        }
        return carrito;
    }

    public boolean eliminarProductoDelCarrito(int idCarrito, int idProducto) {
        String query = "DELETE FROM DetalleCarritoEmpleado WHERE ID_Carrito_empleado = ? AND ID_Producto = ?";
        boolean success = false;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idCarrito);
            stmt.setInt(2, idProducto);

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto del carrito del empleado: " + e.getMessage());
        }

        return success;
    }
}