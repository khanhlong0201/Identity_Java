package java_learn.identity.service.users;

import java_learn.identity.domain.users.User;
import java_learn.identity.domain.users.UserId;

public interface UserUseCaseServiceImpl {
    User findById(UserId userId);
}
