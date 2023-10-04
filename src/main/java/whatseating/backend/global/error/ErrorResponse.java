package whatseating.backend.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import whatseating.backend.global.error.exception.ErrorCode;

@Getter
@Builder
public class ErrorResponse {
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .error(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
