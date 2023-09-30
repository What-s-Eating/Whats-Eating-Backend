package whatseating.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WhatsEatingException extends RuntimeException {
    private final ErrorCode errorCode;
    private String detailMessage;
}
