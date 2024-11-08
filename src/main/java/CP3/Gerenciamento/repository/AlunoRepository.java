package CP3.Gerenciamento.repository;

import CP3.Gerenciamento.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
