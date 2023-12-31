package whatseating.backend.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // User
    USER_INVALID_PASSWORD(403, "USER-001", "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(404, "USER-002", "사용자를 찾을 수 없습니다."),
    USER_DUPLICATED_EMAIL(409, "USER-003", "이미 가입된 이메일입니다."),
    USER_DUPLICATED_NAME(409, "USER-004", "이미 사용중인 이름입니다."),

    // Auth
    TOKEN_INVALID(401, "TOKEN-001", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(401, "TOKEN-002", "만료된 토큰입니다."),
    UNAUTHENTICATED(401, "USER-003","접근 권한이 없습니다"),

    // Place
    PLACE_NOT_FOUND(404, "PLACE-001", "장소를 찾을 수 없습니다."),

    // Reviews
    REVIEWS_NOT_FOUND(404, "REVIEWS-001", "리뷰를 찾을 수 없습니다."),
    REVIEWS_DUPLICATED(409, "REVIEWS-002", "이미 리뷰를 작성했습니다.")
    ;

    private final int status;
    private final String code;
    private final String message;
}
