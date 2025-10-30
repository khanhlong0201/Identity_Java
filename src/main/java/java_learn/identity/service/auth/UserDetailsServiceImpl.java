package java_learn.identity.service.auth;

import jakarta.validation.constraints.NotNull;
import java_learn.identity.bootstrap.configurations.UserAuthentication;
import java_learn.identity.domain.users.User;
import java_learn.identity.repository.database.users.UserMapper;
import java_learn.identity.service.users.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {

  @NotNull UserQueryService userQueryService;

  @NotNull UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userQueryService.findByUsername(username);
    return new UserAuthentication(user);
  }
}
