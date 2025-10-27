package java_learn.identity.service.users;

import java_learn.identity.core.common.exceptions.ServerErrorException;
import java_learn.identity.core.security.models.JwtToken;
import java_learn.identity.core.security.service.JWTTokenService;
import java_learn.identity.domain.users.User;
import java_learn.identity.domain.users.UserId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserUseCaseService {

    @NonNull
    UserQueryService userQueryService;

    @NonNull
    JWTTokenService JwtTokenService;

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
                    user.userId().toString(),
                    refreshClaims
            );
            JwtToken jwtToken = new JwtToken(JwtTokenService.createAccessToken(
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
}
