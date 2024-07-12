package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.User;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registarUsuario {

    public boolean registrarUsuario(User user) {
        boolean registrado = false;
        String sql = "INSERT INTO Usuarios (Nombre, Apellido, Correo, Telefono, Nombre_Usuario, Contraseña, Codigo_RE, Estado, Tipo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3, user.getCorreo());
            ps.setString(4, user.getTelefono());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getCodigo());
            ps.setBoolean(8, user.isEstado());
            ps.setString(9, user.getTipo());

            int rowsAffected = ps.executeUpdate();
            registrado = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrado;
    }
}