package java_learn.identity.controller.api.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java_learn.identity.controller.api.user.validation.UniqueEmail;

@Schema(description = "Tao moi nguoi dung")
public record UserRequest(
    @Schema(description = "Ten dang nhap", example = "khanhlong", required = true)
        @Size(min = 8, max = 20)
        @NotBlank(message = "Username không được để trống")
        String username,
    @Schema(description = "Mat khau dang nhap", example = "KhanhLong@1", required = true)
        @NotBlank(message = "Password không được để trống")
        @Pattern(
            regexp =
                "^(?!.*[\\u00C0-\\u00FF\\u1EA0-\\u1EFF])"
                    + "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>?/`~]).*$",
            message = "Mật khẩu phải có chữ hoa, chữ thường, số, ký tự đặc biệt và không chứa dấu!")
        String password,
    @Schema(description = "email", example = "khanhlong@gmail.com")
        @Email(message = "Email không đúng định dạng")
        @UniqueEmail
        String email) {}
