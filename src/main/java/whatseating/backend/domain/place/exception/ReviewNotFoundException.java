package whatseating.backend.domain.place.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class ReviewNotFoundException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ErrorCode.REVIEWS_NOT_FOUND, ErrorCode.REVIEWS_NOT_FOUND.getMessage());
    }
}
