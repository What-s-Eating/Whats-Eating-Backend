package whatseating.backend.domain.user.user.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class DuplicatedUserEmailException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedUserEmailException();

    private DuplicatedUserEmailException() {
        super(ErrorCode.USER_DUPLICATED_EMAIL, ErrorCode.USER_DUPLICATED_EMAIL.getMessage());
    }
}
