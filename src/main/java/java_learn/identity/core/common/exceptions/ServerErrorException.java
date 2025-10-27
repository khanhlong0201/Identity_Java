package java_learn.identity.core.common.exceptions;

import java_learn.identity.core.constants.CodeErrorCodes;
import org.springframework.http.HttpStatus;
public class ServerErrorException extends ApplicationException {
    public ServerErrorException() {
        super(CodeErrorCodes.SERVER_ERROR, CodeErrorCodes.SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}