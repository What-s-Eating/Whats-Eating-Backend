package whatseating.backend.domain.user.user.exception;

import whatseating.backend.global.error.exception.WhatsEatingException;
import whatseating.backend.global.error.exception.ErrorCode;

public class DuplicatedUserNameException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedUserNameException();

    private DuplicatedUserNameException() {
        super(ErrorCode.USER_DUPLICATED_NAME, ErrorCode.USER_DUPLICATED_NAME.getMessage());
    }
}
