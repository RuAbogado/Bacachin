package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.User;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User getOne(String username, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE username = ? AND password = SHA2(?, 256)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEstado(resultSet.getBoolean("estado"));
                user.setCodigo(resultSet.getString("codigo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}