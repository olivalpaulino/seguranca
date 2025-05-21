package dobackaofront.seguranca.details;

import dobackaofront.seguranca.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converte o papel do usuário em uma autoridade do Spring Security
        String role = user.getRole();
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
        // Por exemplo, se role="USER", ele retorna "ROLE_USER"
    }

    @Override
    public String getPassword() {
        return user.getPassword();  // retorna a senha codificada
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Os métodos abaixo indicam o status da conta. Vamos permitir todos (true) exceto enabled:
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
