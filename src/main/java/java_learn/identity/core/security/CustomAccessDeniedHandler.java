package java_learn.identity.core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import java_learn.identity.core.constants.CodeErrorCodes;
import java_learn.identity.core.constants.RequestHeader;
import java_learn.identity.core.model.RequestId;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java_learn.identity.core.model.ApiErrorResponse;
import java_learn.identity.core.utils.JsonHelper;


@Component/*Đăng ký class này thành bean trong Spring Context, để Spring Security tự động inject và sử dụng khi cấu hình AccessDeniedHandler*/
@NoArgsConstructor/*Tự tạo constructor không tham số, cần thiết để Spring có thể khởi tạo class này tự động*/
/*403 Forbidden (đã đăng nhập nhưng không có quyền).*/
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /*Mục đích: Sinh ra mã Request ID để theo dõi log.
    Nếu request có sẵn header X-REQUEST-ID, thì dùng lại.
    Nếu không, thì tạo ngẫu nhiên bằng UUID.
    → Giúp trace (theo dõi) từng request khi debug/log rất dễ dàng.*/
    public RequestId requestUID(HttpServletRequest request) {
        String headerRequestId = request.getHeader(RequestHeader.REQUEST_ID.getValue());
        return Objects.nonNull(headerRequestId)
                ? new RequestId(headerRequestId)
                : new RequestId(UUID.randomUUID().toString());
    }

    /*Spring Security sẽ gọi hàm này mỗi khi người dùng bị từ chối truy cập.*/
    public void handle(
            HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ApiErrorResponse apiResponse =
                new ApiErrorResponse(
                        this.requestUID(request).value(),
                        CodeErrorCodes.FORBIDDEN.getCode(),
                        CodeErrorCodes.FORBIDDEN.getMessage(),
                        request.getRequestURI(),
                        request.getMethod(),
                        LocalDateTime.now());
        response.setHeader(
                "X-SERVICE-ID",
                Objects.isNull(SystemUtils.getHostName()) ? "DEV" : SystemUtils.getHostName());
        writer.println(JsonHelper.toJson(apiResponse));
    }
}
