package whatseating.backend.global.security.jwt.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class InvalidTokenException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.TOKEN_INVALID, ErrorCode.TOKEN_INVALID.getMessage());
    }
}
