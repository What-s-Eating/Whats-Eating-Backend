package whatseating.backend.domain.restaurants.exception;

import whatseating.backend.global.error.exception.ErrorCode;
import whatseating.backend.global.error.exception.WhatsEatingException;

public class RestaurantsNotFoundException extends WhatsEatingException {
    public static final WhatsEatingException EXCEPTION = new RestaurantsNotFoundException();

    private RestaurantsNotFoundException() {
        super(ErrorCode.RESTAURANTS_NOT_FOUND, ErrorCode.RESTAURANTS_NOT_FOUND.getMessage());
    }
}
