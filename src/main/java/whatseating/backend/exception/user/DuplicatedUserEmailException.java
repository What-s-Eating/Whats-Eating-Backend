package whatseating.backend.exception.user;

import whatseating.backend.exception.enums.ErrorCode;
import whatseating.backend.exception.WhatsEatingException;

public class DuplicatedUserEmailException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedUserEmailException();

    private DuplicatedUserEmailException() {
        super(ErrorCode.DUPLICATED_USER_EMAIL, ErrorCode.DUPLICATED_USER_EMAIL.getMessage());
    }
}
