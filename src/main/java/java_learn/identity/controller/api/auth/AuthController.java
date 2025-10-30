package java_learn.identity.controller.api.auth;

import jakarta.validation.constraints.NotNull;
import java_learn.identity.bootstrap.configurations.UserAuthentication;
import java_learn.identity.controller.api.auth.model.JwtResponse;
import java_learn.identity.controller.api.auth.model.LoginRequest;
import java_learn.identity.core.common.exceptions.ApplicationException;
import java_learn.identity.core.model.ValueResponse;
import java_learn.identity.core.security.models.JwtToken;
import java_learn.identity.domain.constants.ErrorCode;
import java_learn.identity.service.auth.TokenAuthenticationUseCaseService;
import java_learn.identity.service.users.UserUseCaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AuthController implements AuthApi {
  @NotNull TokenAuthenticationUseCaseService tokenAuthenticationUseCaseService;

  @NotNull UserUseCaseServiceImpl userUseCaseServiceImpl;

  @Override
  public ValueResponse<JwtResponse> auth(LoginRequest loginRequest) {
    UserAuthentication userAuthentication =
        (UserAuthentication)
            tokenAuthenticationUseCaseService
                .login(loginRequest.username(), loginRequest.password())
                .orElseThrow(
                    () -> {
                      return new ApplicationException(
                          ErrorCode.LOGIN_INVALID,
                          ErrorCode.LOGIN_INVALID.getMessage(),
                          HttpStatus.UNAUTHORIZED);
                    });
    JwtToken jwtToken = userUseCaseServiceImpl.auth(userAuthentication.user());
    JwtResponse jwtResponse = JwtResponse.of(jwtToken);
    return new ValueResponse<>(jwtResponse);
  }
}
