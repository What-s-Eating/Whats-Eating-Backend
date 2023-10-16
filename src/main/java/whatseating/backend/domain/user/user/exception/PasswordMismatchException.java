package whatseating.backend.domain.user.user.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class PasswordMismatchException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.USER_INVALID_PASSWORD, ErrorCode.USER_INVALID_PASSWORD.getMessage());
    }
}
