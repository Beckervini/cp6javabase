package CP3.Gerenciamento.repository;

import CP3.Gerenciamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // Alterado para retornar Optional<Usuario>
}
