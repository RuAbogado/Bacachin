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

    public int addCategoria(Categorias categoria) {
        String query = "INSERT INTO Categorias (Nombre, Descripcion, Estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Asignar valores a la consulta
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setBoolean(3, categoria.isEstado());

            // Ejecutar la consulta
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                var rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);  // Retorna el ID generado de la categoría
                }
            }
            return -1; // Indica que no se insertó la categoría

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
                boolean estado = rs.getBoolean("Estado"); // Asegúrate de que el nombre de la columna sea "Estado"

                Categorias categoria = new Categorias(id, nombre, descripcion, estado);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    }