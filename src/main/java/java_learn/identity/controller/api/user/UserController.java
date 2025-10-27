package java_learn.identity.controller.api.user;

import java_learn.identity.controller.api.user.model.UserModelMapper;
import java_learn.identity.controller.api.user.model.UserResponse;
import java_learn.identity.core.model.ValueResponse;
import java_learn.identity.domain.users.User;
import java_learn.identity.service.users.UserUseCaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserController implements UserApi {

    @NonNull
    UserUseCaseService userUseCaseService;

    @NonNull
    UserModelMapper userModelMapper;

    @Override
    public ValueResponse<UserResponse> getUserById(int userId){
        User user = userUseCaseService.findById(userId);
        UserResponse userResponse = userModelMapper.toModel(user);
        return new ValueResponse<>(userResponse);
    }
}
