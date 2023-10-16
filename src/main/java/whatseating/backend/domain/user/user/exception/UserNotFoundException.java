package whatseating.backend.domain.user.user.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class UserNotFoundException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage());
    }
}
