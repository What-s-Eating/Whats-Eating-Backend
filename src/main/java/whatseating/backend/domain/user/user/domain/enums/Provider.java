package whatseating.backend.domain.user.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    KAKAO, NAVER, GOOGLE, EMAIL
}
