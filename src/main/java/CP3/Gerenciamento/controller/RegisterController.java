package CP3.Gerenciamento.controller;

import CP3.Gerenciamento.model.Usuario;
import CP3.Gerenciamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegisterController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/register"; // Retorna o template register.html para o formulário de registro
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        // Remova a verificação de papel ADMIN se não for necessário limitar registros
        usuarioService.save(usuario);
        return "redirect:/login"; // Redireciona para a página de login após o registro bem-sucedido
    }
}
