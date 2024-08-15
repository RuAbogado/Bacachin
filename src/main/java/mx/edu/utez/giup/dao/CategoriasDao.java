package mx.edu.utez.giup.dao;

import mx.edu.utez.giup.model.Categorias;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDao {

    // Método para agregar una nueva categoría
    public int addCategoria(Categorias categoria) {
        String query = "INSERT INTO Categorias (Nombre, Descripcion, estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Asignar valores a la consulta
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setBoolean(3, categoria.isEstado()); // Se corrigió el índice y el uso del campo estado

            // Ejecutar la consulta
            int rowsAffected = pstmt.executeUpdate();

            // Obtener el ID de la categoría recién insertada
            if (rowsAffected > 0) {
                return getAllCategorias().stream()
                        .filter(cat -> cat.getNombre().equals(categoria.getNombre()))
                        .findFirst()
                        .map(Categorias::getID_Categoria)
                        .orElse(-1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método para eliminar una categoría por ID
    public static boolean deleteCategoria(int ID_Categoria) {
        String query = "DELETE FROM Categorias WHERE ID_Categoria = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, ID_Categoria);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las categorías
    public List<Categorias> getAllCategorias() {
        List<Categorias> categorias = new ArrayList<>();
        String query = "SELECT * FROM Categorias";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_Categoria");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                boolean estado = rs.getBoolean("estado"); // Obtener el valor del estado

                Categorias categoria = new Categorias(id, nombre, descripcion, estado);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }
}
