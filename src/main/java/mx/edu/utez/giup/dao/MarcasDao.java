package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Marcas;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcasDao {

    // Método para agregar una nueva marca
    public int addMarca(Marcas marca) {
        int marcaId = 0;
        String query = "INSERT INTO Marcas (Nombre, Descripcion, Estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Asignar valores a la consulta
            pstmt.setString(1, marca.getNombre());
            pstmt.setString(2, marca.getDescripcion());
            pstmt.setBoolean(3, marca.getEstado());

            // Ejecutar la consulta
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    marcaId = generatedKeys.getInt(1);
                }
            }

            return marcaId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método para deshabilitar una marca por ID
    public static boolean deshabilitarMarcas(int ID_Marcas) {
        String query = "UPDATE Marcas SET Estado = '0' WHERE ID_Marcas = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, ID_Marcas);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para habilitar una marca por ID
    public static boolean habilitarMarcas(int ID_Marcas) {
        String query = "UPDATE Marcas SET Estado = '1' WHERE ID_Marcas = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, ID_Marcas);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las Marcas
    public List<Marcas> getAllMarcas() {
        List<Marcas> marcas = new ArrayList<>();
        String query = "SELECT * FROM Marcas";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_Marcas");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                boolean estado = rs.getBoolean("Estado"); // Asegúrate de que el nombre de la columna sea "Estado"

                Marcas marca = new Marcas(id, nombre, descripcion, estado);
                marcas.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marcas;
    }
}