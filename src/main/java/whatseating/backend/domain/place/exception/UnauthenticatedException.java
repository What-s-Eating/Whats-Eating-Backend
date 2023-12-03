package whatseating.backend.domain.place.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class UnauthenticatedException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new UnauthenticatedException();

    private UnauthenticatedException() {
        super(ErrorCode.UNAUTHENTICATED, ErrorCode.UNAUTHENTICATED.getMessage());
    }
}