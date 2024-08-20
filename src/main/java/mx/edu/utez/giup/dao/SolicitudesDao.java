package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Solicitudes;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudesDao {

    private Connection connection;

    public SolicitudesDao() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la conexión a la base de datos.", e);
        }
    }

    // Método para obtener todas las solicitudes
    public List<Solicitudes> obtenerTodasLasSolicitudes() {
        List<Solicitudes> solicitudesList = new ArrayList<>();
        String query = "SELECT * FROM Solicitudes";  // Asegúrate de que la tabla sea Solicitudes

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Convertimos java.sql.Date a java.time.LocalDate
                LocalDate fechaSolicitud = rs.getDate("Fecha_Solicitud").toLocalDate();

                Solicitudes solicitud = new Solicitudes(
                        rs.getInt("ID_Solicitud"),
                        rs.getInt("ID_Cliente"),
                        fechaSolicitud,
                        rs.getString("Estado")
                );
                solicitudesList.add(solicitud);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return solicitudesList;
    }

    // Método para crear una solicitud
    public int createSolicitud(Solicitudes solicitud) {
        int generatedId = -1;
        String sql = "INSERT INTO Solicitudes (ID_Cliente, Fecha_Solicitud, Estado) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, solicitud.getID_Cliente());
            statement.setDate(2, Date.valueOf(solicitud.getFecha_Solicitud()));  // Convertimos LocalDate a java.sql.Date
            statement.setString(3, solicitud.getEstado());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}