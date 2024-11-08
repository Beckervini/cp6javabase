package CP3.Gerenciamento.repository;

import CP3.Gerenciamento.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
