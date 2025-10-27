package java_learn.identity.core.security.models;

public record JwtToken(
        String accessToken,
        String refreshToken,
        Long userId,
        String userName,
        String email
) {
}
