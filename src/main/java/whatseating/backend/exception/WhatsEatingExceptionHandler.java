package whatseating.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class WhatsEatingExceptionHandler {
    // 커스텀 예외 처리
    @ExceptionHandler(WhatsEatingException.class)
    public ResponseEntity<ErrorResponse> CustomHandleException(WhatsEatingException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}", e.getErrorCode(), request.getRequestURI(), e.getMessage());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
