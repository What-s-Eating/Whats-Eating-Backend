package whatseating.backend.domain.place.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class PlaceNotFoundException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new PlaceNotFoundException();

    private PlaceNotFoundException() {
        super(ErrorCode.PLACE_NOT_FOUND, ErrorCode.PLACE_NOT_FOUND.getMessage());
    }
}
