package whatseating.backend.domain.user.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class DuplicatedUserEmailException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedUserEmailException();

    private DuplicatedUserEmailException() {
        super(ErrorCode.DUPLICATED_USER_EMAIL, ErrorCode.DUPLICATED_USER_EMAIL.getMessage());
    }
}
