package java_learn.identity.controller.api.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import java_learn.identity.service.users.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
  UserQueryService userQueryService;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (Objects.isNull(email)) {
      return true;
    }
    return !userQueryService.existsByEmail(email);
  }
}
