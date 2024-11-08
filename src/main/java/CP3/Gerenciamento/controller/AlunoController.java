package CP3.Gerenciamento.controller;

import CP3.Gerenciamento.model.Aluno;
import CP3.Gerenciamento.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String listAlunos(Model model) {
        model.addAttribute("alunos", alunoService.findAll());
        return "aluno/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("aluno", new Aluno()); 
        return "aluno/form";
    }

    @PostMapping
    public String saveAluno(@Validated Aluno aluno) {
        alunoService.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/edit/{id}")
    public String editAluno(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", alunoService.findById(id).orElseThrow());
        return "aluno/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAluno(@PathVariable Long id) {
        alunoService.deleteById(id);
        return "redirect:/alunos";
    }
}
