package CP3.Gerenciamento.service;

import CP3.Gerenciamento.model.Role;
import CP3.Gerenciamento.model.Usuario;
import CP3.Gerenciamento.repository.RoleRepository;
import CP3.Gerenciamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Método para salvar um usuário
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para buscar todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Método para buscar um usuário por ID
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para deletar um usuário por ID
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario assignRolesToUser(Usuario usuario, List<String> roleNomes) {
        Set<Role> roles = new HashSet<>();

        for (String roleNome : roleNomes) {
            Optional<Role> roleOpt = roleRepository.findByNome(roleNome);
            roleOpt.ifPresent(roles::add); // Adiciona ao conjunto apenas se estiver presente
        }

        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }
}
