package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Solicitudes;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String query = "SELECT * FROM Ventas";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Solicitudes solicitud = new Solicitudes(
                        rs.getInt("ID_Solicitud"),
                        rs.getInt("ID_Cliente"),
                        rs.getInt("ID_Producto"),
                        rs.getInt("Cantidad"),
                        rs.getDate("Fecha_Solicitud") != null ? rs.getDate("Fecha_Solicitud").toLocalDate() : null,
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
}
