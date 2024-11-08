package CP3.Gerenciamento;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")  // Carrega o perfil de teste
class GerenciamentoApplicationTests {

    @Test
    void contextLoads() {
        // Verifica se o contexto carrega corretamente
    }
}
