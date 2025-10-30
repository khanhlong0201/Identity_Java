package java_learn.identity.service.users;

import java_learn.identity.controller.api.user.model.UserRequest;
import java_learn.identity.core.security.models.JwtToken;
import java_learn.identity.domain.users.User;

public interface UserUseCase {
    User findById(int userId);
    JwtToken auth(User user);
    void saveUser(UserRequest request);
}
