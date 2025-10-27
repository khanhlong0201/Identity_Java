package java_learn.identity.domain.users;

public record User(
        Integer userId,
        String email,
        String password,
        String username,
        Boolean isExpiredPwd
) {
}
