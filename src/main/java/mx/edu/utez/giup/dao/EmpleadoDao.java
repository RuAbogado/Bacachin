package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
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
}
