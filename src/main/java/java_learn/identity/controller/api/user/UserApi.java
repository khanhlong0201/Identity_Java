package java_learn.identity.controller.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_learn.identity.controller.api.user.model.UserResponse;
import java_learn.identity.core.model.ApiErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java_learn.identity.core.model.ValueResponse;



@Tag(name="user", description = "API tac dong den 1 dong trong user")
@ApiResponse(
        responseCode = "400 -> 500",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ApiErrorResponse.class)
        )
)

@RequestMapping("/v1/api/user")
public interface UserApi {
    @Operation(
            summary = "lay user theo id",
            description = "tra ve user theo id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserResponse.class,
                                    description = "tra ve user theo id"

                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Yêu cầu không hợp lệ",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class,
                                    description = "Yêu cầu không hợp lệ"

                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Lỗi máy chủ",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class,
                                    description = "Lỗi máy chủ"

                            )
                    )
            ),

    })
    @GetMapping("/{userId}")
    ValueResponse<UserResponse> getUserById(@PathVariable("userId") int userId);

}
