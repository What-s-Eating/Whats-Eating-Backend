package whatseating.backend.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final ErrorCode status;
    private final String message;
}
