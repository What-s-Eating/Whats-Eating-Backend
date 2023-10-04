package whatseating.backend.domain.user.exception;

import whatseating.backend.global.error.exception.WhatsEatingException;
import whatseating.backend.global.error.exception.ErrorCode;

public class DuplicatedUserNameException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedUserNameException();

    private DuplicatedUserNameException() {
        super(ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage());
    }
}
