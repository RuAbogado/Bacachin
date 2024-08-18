package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.DetalleCarrito;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleCarritoDao {
    private Connection conn;

    public DetalleCarritoDao() throws SQLException {
        this.conn = DatabaseConnectionManager.getConnection();
    }

    public void agregarDetalleCarrito(DetalleCarrito detalleCarrito) throws SQLException {
        String sql = "INSERT INTO Detalle_carrito (ID_Carrito, ID_Producto, Cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detalleCarrito.getIdCarrito());
            stmt.setInt(2, detalleCarrito.getIdProducto());
            stmt.setInt(3, detalleCarrito.getCantidad());
            stmt.executeUpdate();
        }
    }

    // Aquí podrías agregar otros métodos como actualizar, eliminar, o consultar detalles del carrito

    // Cerrar la conexión cuando ya no se necesite
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}