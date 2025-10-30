package java_learn.identity.controller.api.user;

import jakarta.validation.Valid;
import java_learn.identity.controller.api.user.model.UserModelMapper;
import java_learn.identity.controller.api.user.model.UserRequest;
import java_learn.identity.controller.api.user.model.UserResponse;
import java_learn.identity.core.model.ValueResponse;
import java_learn.identity.domain.users.User;
import java_learn.identity.service.users.UserUseCaseServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Slf4j
public class UserController implements UserApi {

    @NonNull
    UserUseCaseServiceImpl userUseCaseServiceImpl;

    @NonNull
    UserModelMapper userModelMapper;

    /*- Kiểm tra TRƯỚC khi method chạy*/
    //@PreAuthorize("@securityUtil.isAdmin()") có thẻ dụng cách này
    @PreAuthorize("authentication.principal.claims['actorType'] == T(java_learn.identity.core.security.ActorType).ADMIN_ACTOR.ordinal()")
    @Override
    public ValueResponse<UserResponse> getUserById(int userId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", auth);

        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Map<String, Object> claims = jwtAuth.getToken().getClaims();
            log.info("All claims: {}", claims);
            log.info("ActorType claim: {}", claims.get("actorType"));
            log.info("ActorType class: {}", claims.get("actorType").getClass());
        }

        User user = userUseCaseServiceImpl.findById(userId);
        UserResponse userResponse = userModelMapper.toModel(user);
        return new ValueResponse<>(userResponse);
    }

    @Override
    public void saveUser(@Valid @RequestBody UserRequest userRequest){
         userUseCaseServiceImpl.saveUser(userRequest);
    }

    // API cho User - chỉ xem thông tin chính mình
    /*- Kiểm tra SAU khi method chạy*/
    @Override
    @PostAuthorize("returnObject.value.userId().toString() == authentication.name")
    public ValueResponse<UserResponse> getMyProfile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = Integer.parseInt(auth.getName());

        User user = userUseCaseServiceImpl.findById(userId);
        UserResponse userResponse = userModelMapper.toModel(user);
        return new ValueResponse<>(userResponse);
    }
}
