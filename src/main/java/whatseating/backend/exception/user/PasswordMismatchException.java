package whatseating.backend.exception.user;

import whatseating.backend.exception.ErrorCode;
import whatseating.backend.exception.WhatsEatingException;

public class PasswordMismatchException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.INVALID_PASSWORD, "비밀번호가 일치하지 않습니다.");
    }
}
