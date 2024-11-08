package CP3.Gerenciamento;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import CP3.Gerenciamento.model.Role;
import CP3.Gerenciamento.model.Usuario;
import CP3.Gerenciamento.repository.RoleRepository;
import CP3.Gerenciamento.repository.UsuarioRepository;

import java.util.Collections;

@SpringBootApplication
public class GerenciamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica se o papel de ADMIN existe, caso contrário, cria
            Role adminRole = roleRepository.findByNome("ADMIN").orElseGet(() -> {
                Role newRole = new Role();
                newRole.setNome("ADMIN");
                return roleRepository.save(newRole);
            });

            // Verifica se o usuário admin existe, caso contrário, cria
            if (usuarioRepository.findByEmail("admin@example.com").isEmpty()) {
                Usuario adminUser = new Usuario();
                adminUser.setNome("Admin");
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setRoles(Collections.singleton(adminRole));
                usuarioRepository.save(adminUser);
            }
        };
    }
}
