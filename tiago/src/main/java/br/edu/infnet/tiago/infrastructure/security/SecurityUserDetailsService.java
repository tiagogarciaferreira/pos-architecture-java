package br.edu.infnet.tiago.infrastructure.security;

import br.edu.infnet.tiago.domain.model.User;
import br.edu.infnet.tiago.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows(UsernameNotFoundException.class)
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (isNull(user)) throw new UsernameNotFoundException(format("User '%s' not found", username));
        return new SecurityUser(user);
    }
}