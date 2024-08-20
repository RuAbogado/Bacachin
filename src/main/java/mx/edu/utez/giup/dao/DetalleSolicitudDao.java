package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleSolicitud;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleSolicitudDao {

    public boolean createDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        boolean isInserted = false;
        String sql = "INSERT INTO Detalles_Solicitudes (ID_Solicitud, ID_Producto, Cantidad, Precio) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalleSolicitud.getID_Solicitud());
            statement.setInt(2, detalleSolicitud.getID_Producto());
            statement.setInt(3, detalleSolicitud.getCantidad());
            statement.setFloat(4, detalleSolicitud.getPrecio());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isInserted;
    }

    public List<DetalleSolicitud> getDetallesBySolicitudId(int solicitudId) {
        List<DetalleSolicitud> detallesList = new ArrayList<>();
        String sql = "SELECT * FROM Detalles_Solicitudes WHERE ID_Solicitud = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, solicitudId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleSolicitud detalle = new DetalleSolicitud(
                        resultSet.getInt("ID_Detalle_Solicitud"),
                        resultSet.getInt("ID_Solicitud"),
                        resultSet.getInt("ID_Producto"),
                        resultSet.getInt("Cantidad"),
                        resultSet.getFloat("Precio")
                );
                detallesList.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detallesList;
    }

    public boolean deleteDetalleSolicitud(int detalleSolicitudId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM Detalles_Solicitudes WHERE ID_Detalle_Solicitud = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalleSolicitudId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
}