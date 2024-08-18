package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Cliente;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private Connection connection;

    public ClienteDao() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    // Método para obtener un cliente por ID
    public Cliente getClienteById(int idCliente) {
        String query = "SELECT * FROM Clientes WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("ID_Cliente"),
                            rs.getInt("ID_Usuario"),
                            rs.getBoolean("Estado")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el cliente por ID", e);
        }
        return null;
    }

    // Método para agregar un cliente
    public boolean addCliente(Cliente cliente) {
        String query = "INSERT INTO Clientes (ID_Usuario, Estado) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cliente.getIdUsuario());
            stmt.setBoolean(2, cliente.isEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar un cliente", e);
        }
    }

    // Método para actualizar un cliente
    public boolean updateCliente(Cliente cliente) {
        String query = "UPDATE Clientes SET ID_Usuario = ?, Estado = ? WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cliente.getIdUsuario());
            stmt.setBoolean(2, cliente.isEstado());
            stmt.setInt(3, cliente.getIdCliente());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar un cliente", e);
        }
    }

    // Método para eliminar un cliente
    public boolean deleteCliente(int idCliente) {
        String query = "DELETE FROM Clientes WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar un cliente", e);
        }
    }

    // Método para obtener todos los clientes
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Clientes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("ID_Cliente"),
                        rs.getInt("ID_Usuario"),
                        rs.getBoolean("Estado")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los clientes", e);
        }
        return clientes;
    }
}