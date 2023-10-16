package whatseating.backend.domain.user.auth.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class InvalidTokenException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new WhatsEatingException();

    private InvalidTokenException() {
        super(ErrorCode.TOKEN_INVALID, ErrorCode.TOKEN_INVALID.getMessage());
    }
}
