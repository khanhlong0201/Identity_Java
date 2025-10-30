package java_learn.identity.core.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodes {
  INCORRECT_OLD_PASSWORD("COU_001", "Mật khẩu cũ không đúng"),
  DUPLICATE_USERNAME("COU_002", "Tên đăng nhập đã tồn tại"),
  DUPLICATE_EMAIL("COU_003", "Email đã tồn tại"),
  CODE_MISMATCH("COU_004", "Mã xác nhận không đúng"),
  RESOURCE_NOT_FOUND("COU_005", "Không tìm thấy dữ liệu"),
  TEAM_CODE_NOT_EXISTS("COU_006", "Mã đội không tồn tại"),
  DEPARTMENT_CODE_NOT_EXISTS("COU_007", "Mã phòng ban không tồn tại"),
  PHONE_EXIST("COU_008", "Số điện thoại đã được đăng ký"),
  PHONE_ERROR("COU_009", "Số điện thoại không hợp lệ"),
  DUPLICATE_USER_OR_EMAIL("COU_010", "Tên đăng nhập hoặc email đã tồn tại"),
  PASSWORD_MATCH_ERROR("COU_011", "Mật khẩu mới không được trùng với mật khẩu cũ"),
  FILE_INVALID("COU_012", "Chỉ hỗ trợ hình ảnh(png, jpeg, jpg), video"),
  EXPORT_ERROR("COU_013", "Lỗi xuất file"),
  CONFIG_NOT_FOUND("COU_014", "Không tìm thấy cấu hình"),
  CONFIG_ERROR("COU_015", "Lỗi kết nối đến hệ thống cấu hình"),
  ROUTE_NOT_FOUND("COU_016", "Không tìm thấy tuyến đường"),
  ROUTE_CONFLICT("COU_017", "Mã tuyến tuần tra đã tồn tại"),
  ROUTE_IS_ACTIVE("COU_018", "Không thể xóa tuyến tuần tra đang được sử dụng"),
  ACCOUNT_ERROR("COU_019", "Tài khoản không khả dụng"),
  TEAM_ERROR("COU_020", "Tài khoản phải thuộc ít nhất 1 tổ"),
  TEAM_ROUTE_ERROR("COU_021", "Tổ chỉ được phép chọn 1 tuyến"),
  TEAM_CODE_EXISTED("COU_022", "Mã tổ đã tồn tại, vui lòng nhập mã khác"),
  LOCALITY_CODE_EXISTED("COU_023", "Mã đội đã tồn tại, vui lòng nhập mã khác"),
  PROPERTY_CODE_EXISTED(
      "COU_024", "Mã tổ chức đã tồn tại trong hệ thống, vui lòng nhập mã tổ chức khác"),
  PROPERTY_EXIST_USER("COU_025", "Tổ chức đã tồn tại người dùng, không thể xóa"),
  PROPERTY_EXIST_CHILD("COU_026", "Tổ chức đã tồn tại tổ chức con, không thể xóa"),
  GROUP_FORBIDDEN("COU_027", "Không thể thay đổi quyền nhóm admin"),
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
