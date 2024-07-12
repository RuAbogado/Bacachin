package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.User;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    // Método para obtener un usuario por nombre de usuario y contraseña
    public User getOne(String username, String password) {
        User user = null;
        String query = "SELECT * FROM Usuarios WHERE Nombre_Usuario = ? AND Contraseña = SHA2(?, 256)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("ID_Usuario"));
                user.setNombre(resultSet.getString("Nombre"));
                user.setApellido(resultSet.getString("Apellido"));
                user.setCorreo(resultSet.getString("Correo"));
                user.setTelefono(resultSet.getString("Telefono"));
                user.setUsername(resultSet.getString("Nombre_Usuario"));
                user.setPassword(resultSet.getString("Contraseña"));
                user.setCodigo(resultSet.getString("Codigo_RE"));
                user.setEstado(resultSet.getBoolean("Estado"));
                user.setTipo(resultSet.getString("Tipo"));
                user.setImagen(resultSet.getString("Imagen"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());
        }
        return user;
    }

    // Método para registrar un nuevo usuario
    public boolean registerUser(User user) {
        boolean isRegistered = false;
        String query = "INSERT INTO Usuarios (Nombre, Apellido, Nombre_Usuario, Telefono, Sexo, Correo, Contraseña, Estado, Codigo_RE, Tipo, Imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?, SHA2(?, 256), ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getTelefono());
            statement.setString(5, user.getSexo());
            statement.setString(6, user.getCorreo());
            statement.setString(7, user.getPassword());
            statement.setBoolean(8, user.isEstado());
            statement.setString(9, user.getCodigo());
            statement.setString(10, user.getTipo());
            statement.setString(11, user.getImagen());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }
        return isRegistered;
    }

    // Otros métodos DAO como updateUser, deleteUser, etc.
}