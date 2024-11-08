package CP3.Gerenciamento.controller;

import CP3.Gerenciamento.model.Curso;
import CP3.Gerenciamento.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "curso/list";
    }

    @GetMapping("/novo")
    public String novoCursoForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/form";
    }

    @PostMapping
    public String salvarCurso(@ModelAttribute Curso curso) {
        cursoService.salvar(curso);
        return "redirect:/admin/cursos";
    }

    @GetMapping("/{id}/editar")
    public String editarCursoForm(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoService.buscarPorId(id));
        return "curso/form";
    }

    @PostMapping("/{id}/excluir")
    public String excluirCurso(@PathVariable Long id) {
        cursoService.excluir(id);
        return "redirect:/admin/cursos";
    }
}
