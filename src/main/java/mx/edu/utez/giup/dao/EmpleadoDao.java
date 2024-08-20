package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Empleado;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoDao {

    private Connection connection;

    public EmpleadoDao() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la conexión a la base de datos.", e);
        }
    }

    // Método para registrar un nuevo empleado
    public boolean registrarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO Empleados (ID_Usuario, Fecha_Contratacion, Salario, Estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, empleado.getID_Usuario());
            stmt.setDate(2, empleado.getFecha_Contratacion());
            stmt.setInt(3, empleado.getSalario());
            stmt.setBoolean(4, empleado.isEstado());  // Asegúrate de que `estado` sea gestionado en tu modelo

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al registrar el empleado: " + e.getMessage(), e);
        }
    }

    // Puedes añadir más métodos aquí para actualizar, eliminar o consultar empleados
}