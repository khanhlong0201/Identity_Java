package java_learn.identity.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Schema(
        example = "",
        requiredMode = Schema.RequiredMode.REQUIRED
)

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiErrorResponse {
    String guid;
    String code;
    String message;
    String path;
    String method;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime timestamp;

    public static ApiErrorResponse ofEmpty(){
        return new ApiErrorResponse("","","","","",LocalDateTime.MIN);
    }
}
