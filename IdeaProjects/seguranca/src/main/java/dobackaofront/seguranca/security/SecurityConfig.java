package dobackaofront.seguranca.security;

import dobackaofront.seguranca.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usa o algoritmo BCrypt para hash de senhas
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Provê a integração do UserDetailsService com o mecanismo de autenticação
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Desativa CSRF para simplificar este exemplo (não é recomendado para produção aplicações web com sessão)
        http.csrf(csrf -> csrf.disable());

        // Configura as URLS e quem pode acessá-las
        http.authorizeHttpRequests((auth) -> auth
                // Permite acesso público à página de login e ao console H2
                .requestMatchers("/login", "/h2-console/**").permitAll()
                // Qualquer outra requisição requer autenticação:
                .anyRequest().authenticated()
        );

        // Configura o formulário de login customizado
        http.formLogin((form) -> form
                .loginPage("/login")           // página de login customizada
                .permitAll()                   // permite a todos acessarem a página de login
                .defaultSuccessUrl("/dashboard", true)  // página padrão após login (true = sempre vai pra essa página após login bem-sucedido)
                .failureUrl("/login?error=true")        // se falhar, adiciona parâmetro de erro
        );

        // Configura logout
        http.logout((logout) -> logout
                .logoutSuccessUrl("/login?logout=true")  // após fazer logout, vai para tela de login
                .permitAll()
        );

        // **Importante:** registrar o AuthenticationProvider customizado
        http.authenticationProvider(authenticationProvider());

        // Opcional: permitir visualização do console H2 (desabilitando proteção de frame do navegador)
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp.policyDirectives("frame-ancestors 'non';")));

        return http.build();
    }
}
