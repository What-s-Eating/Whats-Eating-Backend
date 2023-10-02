package whatseating.backend.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    // User
    INVALID_PASSWORD(403, "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    DUPLICATED_USER_EMAIL(409, "이미 가입된 이메일입니다."),
    DUPLICATED_USER_NAME(409, "이미 사용중인 이름입니다."),
    ;

    private final int status;
    private final String message;
}