package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleCarritoAdmin;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;

public class DetalleCarritoAdminDao {


    public List<DetalleCarritoAdmin> getDetallesByCarritoId(int idCarritoAdmin) {
        List<DetalleCarritoAdmin> detalles = new ArrayList<>();
        String sql = "SELECT * FROM Detalle_carrito_admin WHERE ID_Carrito_admin = ?";

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
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

    // Método para obtener un detalle del carrito por ID de carrito y ID de producto
    public DetalleCarritoAdmin obtenerDetallePorCarritoYProducto(int idCarritoAdmin, int idProducto) {
        DetalleCarritoAdmin detalle = null;
        String sql = "SELECT * FROM Detalle_carrito_admin WHERE ID_Carrito_admin = ? AND ID_Producto = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCarritoAdmin);
            statement.setInt(2, idProducto);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                detalle = new DetalleCarritoAdmin();
                detalle.setIdDetalleCarritoAdmin(resultSet.getInt("ID_DetalleCarritoAdmin"));
                detalle.setIdCarritoAdmin(resultSet.getInt("ID_Carrito_admin"));
                detalle.setIdProducto(resultSet.getInt("ID_Producto"));
                detalle.setCantidad(resultSet.getInt("Cantidad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalle;
    }

    // Método para actualizar la cantidad de un producto en el carrito
    public void actualizarCantidad(int idCarritoAdmin, int idProducto, int nuevaCantidad) {
        String sql = "UPDATE Detalle_carrito_admin SET Cantidad = ? WHERE ID_Carrito_admin = ? AND ID_Producto = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idCarritoAdmin);
            statement.setInt(3, idProducto);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo detalle en el carrito
    public void insertarDetalle(DetalleCarritoAdmin detalle) {
        String sql = "INSERT INTO Detalle_carrito_admin (ID_Carrito_admin, ID_Producto, Cantidad) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalle.getIdCarritoAdmin());
            statement.setInt(2, detalle.getIdProducto());
            statement.setInt(3, detalle.getCantidad());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}