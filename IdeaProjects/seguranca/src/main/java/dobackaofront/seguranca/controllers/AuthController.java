package dobackaofront.seguranca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        // Retorna o nome da view do formulário de login (Thymeleaf template "login.html")
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        // Adiciona o nome do usuário logado para mostrar na página
        model.addAttribute("username", principal.getName());
        return "dashboard"; // Retorna o template "dashboard.html"
    }

    // Opcional: podemos mapear a raiz para redirecionar para /dashboard
    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }
}
