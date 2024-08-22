package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Solicitudes;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudesDao {

    // Método para obtener todas las solicitudes con detalles adicionales
    public List<Solicitudes> obtenerTodasLasSolicitudes() {
        List<Solicitudes> solicitudesList = new ArrayList<>();
        String query = "SELECT s.ID_Solicitud, s.ID_Cliente, s.Fecha_Solicitud, s.Estado, " +
                "ds.Cantidad, ds.Precio, p.Nombre AS Nombre_Producto " +
                "FROM Solicitudes s " +
                "JOIN Detalles_Solicitudes ds ON s.ID_Solicitud = ds.ID_Solicitud " +
                "JOIN Productos p ON ds.ID_Producto = p.ID_Producto";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LocalDate fechaSolicitud = rs.getDate("Fecha_Solicitud").toLocalDate();

                // Construir el objeto Solicitudes con la información adicional
                Solicitudes solicitud = new Solicitudes(
                        rs.getInt("ID_Solicitud"),
                        rs.getInt("ID_Cliente"),
                        fechaSolicitud,
                        rs.getString("Estado"),
                        rs.getInt("Cantidad"),  // Cantidad de la tabla Detalles_Solicitudes
                        rs.getDouble("Precio"), // Precio de la tabla Detalles_Solicitudes
                        rs.getString("Nombre_Producto")  // Nombre del producto de la tabla Productos
                );
                solicitudesList.add(solicitud);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Agregar un mensaje de error o lanzar una excepción personalizada
        }
        return solicitudesList;
    }

    // Método para buscar solicitudes por nombre de producto
    public List<Solicitudes> buscarSolicitudesPorProducto(String productName) {
        List<Solicitudes> solicitudesList = new ArrayList<>();
        String query = "SELECT s.ID_Solicitud, s.ID_Cliente, s.Fecha_Solicitud, s.Estado, " +
                "ds.Cantidad, ds.Precio, p.Nombre AS Nombre_Producto " +
                "FROM Solicitudes s " +
                "JOIN Detalles_Solicitudes ds ON s.ID_Solicitud = ds.ID_Solicitud " +
                "JOIN Productos p ON ds.ID_Producto = p.ID_Producto " +
                "WHERE p.Nombre LIKE ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "%" + productName + "%");
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    LocalDate fechaSolicitud = rs.getDate("Fecha_Solicitud").toLocalDate();

                    // Construir el objeto Solicitudes con la información adicional
                    Solicitudes solicitud = new Solicitudes(
                            rs.getInt("ID_Solicitud"),
                            rs.getInt("ID_Cliente"),
                            fechaSolicitud,
                            rs.getString("Estado"),
                            rs.getInt("Cantidad"),  // Cantidad de la tabla Detalles_Solicitudes
                            rs.getDouble("Precio"), // Precio de la tabla Detalles_Solicitudes
                            rs.getString("Nombre_Producto")  // Nombre del producto de la tabla Productos
                    );
                    solicitudesList.add(solicitud);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Agregar un mensaje de error o lanzar una excepción personalizada
        }
        return solicitudesList;
    }

    // Método para crear una solicitud
    public int createSolicitud(Solicitudes solicitud) {
        int generatedId = -1;
        String sql = "INSERT INTO Solicitudes (ID_Cliente, Fecha_Solicitud, Estado) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, solicitud.getID_Cliente());
            statement.setDate(2, Date.valueOf(solicitud.getFecha_Solicitud()));
            statement.setString(3, solicitud.getEstado());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Agregar un mensaje de error o lanzar una excepción personalizada
        }
        return generatedId;
    }

    // Método para actualizar el estado de una solicitud usando ID_Solicitud como int
    public boolean actualizarEstadoSolicitud(int idSolicitud, String nuevoEstado) {
        String sql = "UPDATE Solicitudes SET Estado = ? WHERE ID_Solicitud = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nuevoEstado);
            statement.setInt(2, idSolicitud);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Agregar un mensaje de error o lanzar una excepción personalizada
            return false;
        }
    }
}
