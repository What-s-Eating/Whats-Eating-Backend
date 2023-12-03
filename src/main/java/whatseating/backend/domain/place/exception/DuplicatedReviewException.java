package whatseating.backend.domain.place.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class DuplicatedReviewException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new DuplicatedReviewException();

    private DuplicatedReviewException() {
        super(ErrorCode.REVIEWS_DUPLICATED, ErrorCode.REVIEWS_DUPLICATED.getMessage());
    }
}
