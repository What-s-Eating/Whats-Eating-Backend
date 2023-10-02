package whatseating.backend.exception.user;

import whatseating.backend.exception.WhatsEatingException;
import whatseating.backend.exception.enums.ErrorCode;

public class DuplicatedUserNameException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedUserNameException();

    private DuplicatedUserNameException() {
        super(ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage());
    }
}
