package java_learn.identity.core.common.exceptions;

import java_learn.identity.domain.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public class CoreException extends ApplicationException {
    public CoreException() {
        super(
                ErrorCode.SYSTEM_ERROR,
                ErrorCode.SYSTEM_ERROR.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
