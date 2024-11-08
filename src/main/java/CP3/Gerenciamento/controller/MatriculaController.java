package CP3.Gerenciamento.controller;

import CP3.Gerenciamento.service.CursoService;
import CP3.Gerenciamento.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aluno/matriculas")
public class MatriculaController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public String listarCursosParaMatricula(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "matricula/list";
    }

    @GetMapping("/{cursoId}/inscrever")
    public String inscreverEmCurso(@PathVariable Long cursoId, @RequestParam Long usuarioId) {
        matriculaService.inscrever(cursoId, usuarioId);
        return "redirect:/aluno/matriculas";
    }

    @GetMapping("/visualizar")
    public String visualizarMinhasMatriculas(@RequestParam Long usuarioId, Model model) {
        model.addAttribute("matriculas", matriculaService.minhasMatriculas(usuarioId));
        return "matricula/list";
    }
}
