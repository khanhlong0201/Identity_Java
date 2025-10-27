package java_learn.identity.core.common.exceptions;

import java_learn.identity.core.constants.CodeErrorCodes;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException() {
        super(CodeErrorCodes.RESOURCE_NOT_FOUND,CodeErrorCodes.RESOURCE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
