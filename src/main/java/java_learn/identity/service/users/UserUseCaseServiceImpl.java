package java_learn.identity.service.users;

import java_learn.identity.controller.api.user.model.UserModelMapper;
import java_learn.identity.controller.api.user.model.UserRequest;
import java_learn.identity.core.common.exceptions.ApplicationException;
import java_learn.identity.core.common.exceptions.ServerErrorException;
import java_learn.identity.core.constants.ErrorCode;
import java_learn.identity.core.security.ActorType;
import java_learn.identity.core.security.models.JwtToken;
import java_learn.identity.core.security.service.JWTTokenService;
import java_learn.identity.domain.users.User;
import java_learn.identity.domain.users.UserId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserUseCaseServiceImpl implements UserUseCase {

    @NonNull
    UserQueryService userQueryService;

    @NonNull
    JWTTokenService JwtTokenService;

    @NonNull
    UserModelMapper userModelMapper;

    @NonNull
    UserCommandService userCommandService;

    public User findById(int userId){
       return userQueryService.findById(new UserId(userId));
    }

    @Transactional
    public JwtToken auth(User user) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> refreshClaims = new HashMap<>();
//        String sid = AuthenticationContext.getOrCreateSessionId();
        try {
            String refreshToken = JwtTokenService.createRefreshToken(
                    ActorType.ADMIN_ACTOR,
                    user.userId().toString(),
                    refreshClaims
            );
            JwtToken jwtToken = new JwtToken(JwtTokenService.createAccessToken(
                    ActorType.ADMIN_ACTOR,
                    user.userId().toString(), claims),
                    refreshToken,
                    Long.valueOf(user.userId()),
                    user.username(),
                    user.email());
            return jwtToken;
        } catch (Exception e) {
            throw new ServerErrorException();
        }
    }

    public void saveUser(UserRequest request) {
        boolean exists = userQueryService.existsByUsername(request.username());
        if(exists){
            throw new ApplicationException(
                    ErrorCode.DUPLICATE_USERNAME,
                    ErrorCode.DUPLICATE_USERNAME.getMessage(),
                    HttpStatus.CONFLICT);
        }
        User user = userModelMapper.toDto(request);
        userCommandService.save(user);
    }
}
