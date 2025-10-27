package java_learn.identity.core.common.exceptions;

import java_learn.identity.core.constants.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@FieldDefaults(makeFinal = true)
@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {
    ErrorCodes errorCode;
    String message;
    HttpStatus httpStatus;

    public String asMessage(){
        return StringUtils.hasText(this.message) ? this.message : this.errorCode.getMessage();
    }
}
