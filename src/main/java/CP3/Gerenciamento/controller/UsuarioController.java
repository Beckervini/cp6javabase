package CP3.Gerenciamento.controller;

import CP3.Gerenciamento.model.Usuario;
import CP3.Gerenciamento.model.Role;
import CP3.Gerenciamento.service.RoleService;
import CP3.Gerenciamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/add")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", roleService.findAll());
        return "usuario/register";
    }

    @PostMapping("/register")
public String registerUser(@Validated Usuario usuario, @RequestParam List<Long> roles) {
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

    // Especificando explicitamente o tipo de retorno do map
    Set<Role> roleSet = roles.stream()
                             .<Role>map(roleId -> roleService.findById(roleId)
                             .orElseThrow(() -> new RuntimeException("Role with ID " + roleId + " not found")))
                             .collect(Collectors.toSet());

    usuario.setRoles(roleSet);
    usuarioService.save(usuario);
    return "redirect:/login";
}


    @GetMapping("/list")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll(); // Verifique se este método está correto
        model.addAttribute("usuarios", usuarios);
        return "usuario/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roleService.findAll());
        return "usuario/form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Validated Usuario usuario, @RequestParam List<String> roles) {
        usuario.setId(id);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.assignRolesToUser(usuario, roles);
        usuarioService.save(usuario);
        return "redirect:/usuarios/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios/list";
    }
}
