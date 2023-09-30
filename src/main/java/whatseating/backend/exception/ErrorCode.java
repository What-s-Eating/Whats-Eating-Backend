package whatseating.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    // User
    INVALID_USER(400, "유효하지 않은 사용자입니다."),
    INVALID_EMAIL(400, "유효하지 않은 이메일입니다."),
    INVALID_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),
    FORBIDDEN(403, "권한이 없습니다."),
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    DUPLICATED_USER(409, "이미 존재하는 사용자입니다."),
    DUPLICATED_NICKNAME(409, "이미 존재하는 닉네임입니다."),
    DUPLICATED_EMAIL(409, "이미 존재하는 이메일입니다."),
    DUPLICATED_PHONE_NUMBER(409, "이미 존재하는 전화번호입니다."),

    // Restaurant, Cafe
    RESTAURANT_NOT_FOUND(404, "식당을 찾을 수 없습니다."),
    CAFE_NOT_FOUND(404, "카페를 찾을 수 없습니다."),
    ;

    private final int status;
    private final String message;
}
