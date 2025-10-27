package java_learn.identity.controller.api.auth;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java_learn.identity.controller.api.auth.model.JwtResponse;
import java_learn.identity.controller.api.auth.model.LoginRequest;
import java_learn.identity.core.model.ApiErrorResponse;
import java_learn.identity.core.model.ValueResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Hidden
@Tag(name = "auth", description = "authen")
@RequestMapping("v1/api/auth")
@ApiResponse(
        responseCode = "400 -> 500", content = {
                @Content(schema = @Schema(implementation = ApiErrorResponse.class))
        }
)
public interface AuthApi {

    @Operation(operationId = "auth", summary = "auth", description = "")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JwtResponse.class))
    })
    @PostMapping("/login")
    ValueResponse<JwtResponse> auth(@Valid @RequestBody LoginRequest loginRequest);
}
