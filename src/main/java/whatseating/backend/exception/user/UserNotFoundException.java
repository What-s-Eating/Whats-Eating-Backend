package whatseating.backend.exception.user;

import whatseating.backend.exception.ErrorCode;
import whatseating.backend.exception.WhatsEatingException;

public class UserNotFoundException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage());
    }
}
