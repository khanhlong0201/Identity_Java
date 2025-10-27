package java_learn.identity.controller.api.auth.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull(message = "Ten username khong duoc rong")
        @Size(min = 3, max = 256, message = "Do dai username phai trong khoang 3 den 356 ")
        String username,
        @NotNull(message = "Mat khau khong duoc rong")
        @Size(min = 3, max = 256, message = "Do dai mat khau phai trong khoang 3 den 356 ")
        String password
) {
}
