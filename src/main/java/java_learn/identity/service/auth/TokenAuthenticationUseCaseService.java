package java_learn.identity.service.auth;
;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TokenAuthenticationUseCaseService {

    @NonNull
    UserDetailsServiceImpl userDetailsService;

    @NonNull
    PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<UserDetails> login(String username, String password) {
        return Optional.ofNullable(userDetailsService
                .loadUserByUsername(username));
//                .filter(user -> passwordEncoder.matches(password,user.getPassword()));
    }

}
