package CP3.Gerenciamento.service;

import CP3.Gerenciamento.model.Role;
import CP3.Gerenciamento.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Método para encontrar todos os Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    // Método para encontrar um Role por ID
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    // Novo método para encontrar um Role por nome
    public Optional<Role> findByNome(String nome) {
        return roleRepository.findByNome(nome);
    }
}
