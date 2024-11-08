package CP3.Gerenciamento.service;

import CP3.Gerenciamento.model.Matricula;
import CP3.Gerenciamento.model.Curso;
import CP3.Gerenciamento.model.Usuario;
import CP3.Gerenciamento.repository.MatriculaRepository;
import CP3.Gerenciamento.repository.CursoRepository;
import CP3.Gerenciamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void inscrever(Long cursoId, Long usuarioId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Matricula matricula = new Matricula();
        matricula.setCurso(curso);
        matricula.setUsuario(usuario);
        matriculaRepository.save(matricula);
    }

    public List<Matricula> minhasMatriculas(Long usuarioId) {
        return matriculaRepository.findByUsuarioId(usuarioId);
    }
}
