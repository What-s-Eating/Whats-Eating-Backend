package whatseating.backend.exception.user;

import whatseating.backend.exception.enums.ErrorCode;
import whatseating.backend.exception.WhatsEatingException;

public class PasswordMismatchException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
    }
}
