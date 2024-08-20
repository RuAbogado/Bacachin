package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.User;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    // Método para obtener un usuario por correo y contraseña
    public User getOne(String correo, String contraseña) {
        User user = null;
        String query = "SELECT * FROM Usuarios WHERE Correo = ? AND Contraseña = SHA2(?, 256)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, correo);
            statement.setString(2, contraseña);
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
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());
        }
        return user;
    }

    // Método para obtener el ID de un empleado
    public int getEmpleadoId(int userId) {
        int idEmpleado = 0;
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            String sql = "SELECT ID_Empleado FROM Empleados WHERE ID_Usuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idEmpleado = rs.getInt("ID_Empleado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEmpleado;
    }

    // Método para obtener el ID de un administrador
    public int getAdminId(int userId) {
        int idAdmin = 0;
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            String sql = "SELECT ID_Admin FROM Administradores WHERE ID_Usuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idAdmin = rs.getInt("ID_Admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAdmin;
    }

    // Método para registrar un nuevo usuario y devolver su ID
    public int registerUser(User user) throws SQLException {
        int userId = -1;
        String query = "INSERT INTO Usuarios (Nombre, Apellido, Nombre_Usuario, Telefono, Correo, Contraseña, Estado, Codigo_RE, Tipo) " +
                "VALUES (?, ?, ?, ?, ?, SHA2(?, 256), ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getTelefono());
            statement.setString(5, user.getCorreo());
            statement.setString(6, user.getPassword());
            statement.setBoolean(7, user.isEstado());
            statement.setString(8, user.getCodigo());
            statement.setString(9, user.getTipo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el usuario: " + e.getMessage(), e);
        }
        return userId;
    }

    // Método para obtener el tipo de usuario por correo y contraseña
    public String getTipoUsuario(String correo, String contraseña) {
        String tipo = null;
        String query = "SELECT Tipo FROM Usuarios WHERE Correo = ? AND Contraseña = SHA2(?, 256)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, correo);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipo = resultSet.getString("Tipo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de usuario: " + e.getMessage());
        }
        return tipo;
    }

    // Método para actualizar un usuario
    public boolean actualizarUsuario(User user) {
        boolean actualizado = false;
        String query = "UPDATE Usuarios SET nombre = ?, apellido = ?, username = ?, telefono = ?, correo = ?, password = SHA2(?, 256), estado = ?, codigo = ?, tipo = ? WHERE ID_Usuario = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getTelefono());
            statement.setString(5, user.getCorreo());
            statement.setString(6, user.getPassword());
            statement.setBoolean(7, user.isEstado());
            statement.setString(8, user.getCodigo());
            statement.setString(9, user.getTipo());
            statement.setInt(10, user.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
        return actualizado;
    }

    // Método para obtener un usuario por ID
    public User getOne(int idUsuario) {
        User user = null;
        String query = "SELECT * FROM Usuarios WHERE ID_Usuario = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("ID_Usuario"));
                user.setNombre(resultSet.getString("nombre"));
                user.setApellido(resultSet.getString("apellido"));
                user.setCorreo(resultSet.getString("correo"));
                user.setTelefono(resultSet.getString("telefono"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setCodigo(resultSet.getString("codigo"));
                user.setEstado(resultSet.getBoolean("estado"));
                user.setTipo(resultSet.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());
        }
        return user;
    }

    // Método para obtener el ID_Cliente basado en el ID_Usuario
    public int getClienteId(int idUsuario) {
        int idCliente = -1;
        String query = "SELECT ID_Cliente FROM Clientes WHERE ID_Usuario = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idCliente = resultSet.getInt("ID_Cliente");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID_Cliente: " + e.getMessage());
        }
        return idCliente;
    }
}
