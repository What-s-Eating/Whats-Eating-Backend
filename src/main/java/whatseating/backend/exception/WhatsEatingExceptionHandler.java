package whatseating.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class WhatsEatingExceptionHandler {
    @ExceptionHandler(WhatsEatingException.class)
    public ErrorResponse handleException(WhatsEatingException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getErrorCode(), request.getRequestURI(), e.getDetailMessage());

        return ErrorResponse.builder()
                .status(e.getErrorCode())
                .message(e.getDetailMessage())
                .build();
    }
}
