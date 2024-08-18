package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Carrito;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;

public class CarritoDao {

    // Método para obtener un carrito por el ID del usuario
    public Carrito getCarritoByUserId(int userId) {
        Carrito carrito = null;
        String sql = "SELECT * FROM Carrito WHERE ID_Cliente = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
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
            e.printStackTrace();
        }
        return carrito;
    }

    // Método para crear un nuevo carrito
    public Carrito createCarrito(Carrito carrito) {
        String sql = "INSERT INTO Carrito (ID_Cliente, Fecha_Creacion) VALUES (?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, carrito.getID_Cliente());
            statement.setDate(2, carrito.getFecha_Creacion());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    carrito.setID_Carrito(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrito;
    }

    // Otros métodos relacionados con el carrito podrían ir aquí
}