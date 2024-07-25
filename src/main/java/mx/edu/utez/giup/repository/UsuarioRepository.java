package mx.edu.utez.giup.repository;

import mx.edu.utez.giup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
