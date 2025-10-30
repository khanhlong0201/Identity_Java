package java_learn.identity.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
@AllArgsConstructor
@Getter
public enum CodeErrorCodes implements ErrorCodes {
  SYSTEM_ERROR("SYS001", "Có lỗi phát sinh. Vui lòng liên hệ để xử lý."),
  CONFLICT("SYS002", "Xung đột dữ liệu."),
  FORBIDDEN("SYS003", "Không có quyền truy cập dữ liệu."),
  REQUEST_INVALID("SYS004", "Dữ liệu yêu cầu không hợp lệ."),
  RESOURCE_NOT_FOUND("SYS005", "Dữ liệu không tồn tại."),
  SERVER_ERROR("SYS006", "Có lỗi phát sinh. Vui lòng liên hệ để xử lý."),
  TOKEN_INVALID(
      "SYS007",
      "Phiên làm việc đã hết hạn.\n Vui lòng thực hiện đăng nhập lại để sử dụng ứng dụng."),
  SYSTEM_AUTHORIZATION("SYS008", "Không có quyền truy cập dữ liệu."),
  SERVICE_UNAVAILABLE("SYS009", "Dịch vụ không có sẵn"),
  BAD_REQUEST("SYS010", "Dữ liệu yêu cầu không hợp lệ."),
  TOO_MANY_REQUESTS("SYS011", "Too Many Requests");

  String code;
  String message;

  public static CodeErrorCodes getCodeErrorCodes(String value) {
    for (CodeErrorCodes code : values()) {
      if (code.getCode().equals(value)) {
        return code;
      }
    }
    return null;
  }
}
