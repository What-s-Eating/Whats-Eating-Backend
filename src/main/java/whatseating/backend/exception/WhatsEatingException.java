package whatseating.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import whatseating.backend.exception.enums.ErrorCode;

@Getter
@RequiredArgsConstructor
public class WhatsEatingException extends RuntimeException {
    private ErrorCode errorCode;

    private String detailMessage;

    public WhatsEatingException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
