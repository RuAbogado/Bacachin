package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.User;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;
import java.sql.*;

public class AdminDao {
    private final String GET_USER_BY_ID = "SELECT * FROM usuarios WHERE ID_Usuario = ?";

    public User getOne(int idUsuario) {
        User user = null;
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_USER_BY_ID)) {

            pst.setInt(1, idUsuario);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("ID_Usuario"));
                    user.setNombre(rs.getString("Nombre"));
                    user.setApellido(rs.getString("Apellido"));
                    user.setUsername(rs.getString("Nombre_Usuario"));
                    user.setTelefono(rs.getString("Telefono"));
                    user.setCorreo(rs.getString("Correo"));
                    user.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
