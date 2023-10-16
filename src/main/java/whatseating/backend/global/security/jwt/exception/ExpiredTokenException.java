package whatseating.backend.global.security.jwt.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class ExpiredTokenException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.TOKEN_EXPIRED, ErrorCode.TOKEN_EXPIRED.getMessage());
    }
}
