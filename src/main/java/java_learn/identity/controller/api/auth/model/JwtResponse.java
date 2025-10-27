package java_learn.identity.controller.api.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java_learn.identity.core.security.models.JwtToken;

@Schema(example = """
        {
            "accessToken": "",
            "refreshToken": "",
            "userId": "",
            "userName": "",
            "email": "",
            "isExpiredPwd" :"",
            "roles": []
        }
        """, requiredMode = Schema.RequiredMode.REQUIRED)
public record JwtResponse(
        String accessToken,
        String refreshToken,
        long userId,
        String userName,
        String email
) {
    public static JwtResponse ofEmpty() {
        return new JwtResponse(
                "",
                "",
                -1,
                "",
                ""
        );
    }

    public static JwtResponse of(JwtToken jwtToken) {
        return new JwtResponse(
                jwtToken.accessToken(),
                jwtToken.refreshToken(),
                jwtToken.userId(),
                jwtToken.userName(),
                jwtToken.email()
        );
    }
}
