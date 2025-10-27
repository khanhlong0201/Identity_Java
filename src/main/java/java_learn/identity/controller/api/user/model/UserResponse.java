package java_learn.identity.controller.api.user.model;

public record UserResponse(
        Integer userId,
        String email,
        String password,
        String username,
        Boolean isExpiredPwd
) {
}
