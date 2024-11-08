package CP3.Gerenciamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // Retorna o nome do template home.html
    }

    @GetMapping("/")
    public String root() {
        return "home"; // Redireciona a raiz (/) para o template home.html
    }
}
