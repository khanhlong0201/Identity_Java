package java_learn.identity.domain.constants;

import java_learn.identity.core.constants.ErrorCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public enum ErrorCode implements ErrorCodes {
  SYSTEM_ERROR("AUH_001", "SYSTEM_ERROR"),
  PASSWORD_ERROR("AUH_002", "PASSWORD_ERROR"),
  USERNAME_EXISTS("AUH_003", "USERNAME_ALREADY_EXISTS"),
  EMAIL_EXISTS("AUH_004", "Email đã tồn tại"),
  CODE_SEND_TO_EMAIL_NOT_EXISTS("AUH_005", "CODE_SEND_TO_EMAIL_NOT_EXISTS"),
  LOGIN_INVALID("AUH_006", "Tài khoản hoặc mật khẩu không đúng"),
  EMAIL_NOT_FOUND("AUH_007", "Email không tồn tại"),
  CODE_MISMATCH("AUH_008", "Mã xác nhận không đúng"),
  INCORRECT_OLD_PASSWORD("AUH_009", "Mật khẩu cũ không đúng"),
  TOKEN_INVALID("AUH_010", "Phiên đăng nhập không hợp lệ, vui lòng đăng nhập lại"),
  URL_ERROR("AUH_011", "Đường dẫn không tồn tại"),
  LIMIT_LOGIN("AUH_012", "Đăng nhập sai quá nhiều lần"),
  LOGIN_ALREADY(
      "AUH_013", "Tài khoản đã đăng nhập ở thiết bị khác, vui lòng đăng xuất thiết bị cũ"),
  USER_BLOCK("AUH_014", "Tài khoản đã bị khoá hoặc không có quyền truy cập"),
  PHONE_NOT_FOUND("AUH_015", "Không tìm thấy số điện thoại!"),
  SEND_OTP_ERROR("AUH_016", "Gửi mã OTP đang bị lỗi, xin vui lòng thử lại"),
  NOTIFICATION_ERROR("AUH_017", "Lỗi kết nối đến hệ thống gửi thông báo"),
  OTP_EXISTED("AUH_018", "Mã OTP đã được gửi"),
  OTP_NOT_FOUND("AUH_019", "Mã OTP không hợp lệ"),
  OTP_INVALID("AUH_020", "Mã OTP không khớp với thông tin đăng ký"),
  CONFIG_NOT_FOUND("AUH_021", "Không tìm thấy cấu hình"),
  CONFIG_ERROR("AUH_022", "Lỗi kết nối đến hệ thống cấu hình"),
  CURRENT_PASSWORD_INCORRECT("AUH_023", "Mật khẩu cũ không đúng."),
  DUPLICATE_NEW_PASSWORD("AUH_024", "Mật khẩu mới không được trùng với mật khẩu cũ"),
  USER_NOT_EXISTS("AUH_025", "Tài khoản không tồn tại"),
  SOLDIER_APP_NOT_FOUND("AUH_026", "Tài khoản không được truy cập vào ứng dụng"),
  ;

  final String code;
  final String message;

  public static ErrorCode getErrorCodes(String value) {
    for (ErrorCode a : values()) {
      if (a.code.equalsIgnoreCase(value)) {
        return a;
      }
    }
    return null;
  }
}
