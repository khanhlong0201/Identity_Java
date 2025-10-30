package java_learn.identity.service.users;

import jakarta.validation.constraints.NotNull;
import java_learn.identity.core.common.exceptions.ApplicationException;
import java_learn.identity.core.common.exceptions.ResourceNotFoundException;
import java_learn.identity.core.security.models.JwtToken;
import java_learn.identity.core.security.service.JWTTokenService;
import java_learn.identity.domain.constants.ErrorCode;
import java_learn.identity.domain.users.User;
import java_learn.identity.domain.users.UserId;
import java_learn.identity.repository.database.users.UserEntity;
import java_learn.identity.repository.database.users.UserMapper;
import java_learn.identity.repository.database.users.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserQueryService {

    @NonNull
    UserRepository repository;

    @NonNull
    UserMapper userMapper;

    @NotNull
    JWTTokenService jwtTokenService;

    public User findById(UserId userId){
        UserEntity userEntity = repository.findById(userId.value())
                .orElseThrow(ResourceNotFoundException::new);
        return userMapper.toDto(userEntity);
    }

    public User findByUsername(String username){
        UserEntity userEntity = repository.findByUsername(username)
                .orElseThrow(() -> {
                    return new ApplicationException(ErrorCode.LOGIN_INVALID,
                            ErrorCode.LOGIN_INVALID.getMessage(),
                            HttpStatus.UNAUTHORIZED);
                });
        return userMapper.toDto(userEntity);
    }

    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String username){
        return repository.existsByEmail(username);
    }
}
