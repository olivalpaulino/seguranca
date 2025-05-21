package dobackaofront.seguranca;

import dobackaofront.seguranca.entity.User;
import dobackaofront.seguranca.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SegurancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegurancaApplication.class, args);
	}

	// Insere um usuário padrão ao iniciar, para fins de teste
	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Evita duplicação: insere apenas se não existir
			if (userRepository.findByUsername("testuser").isEmpty()) {
				User user = new User(
						"testuser",
						passwordEncoder.encode("12345"),  // codifica a senha "12345"
						"USER",
						true
				);
				userRepository.save(user);
				System.out.println("Usuário inicial criado: " + user.getUsername() + " / senha: 12345");
			}
		};
	}
}
