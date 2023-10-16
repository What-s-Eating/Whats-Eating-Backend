package whatseating.backend.domain.user.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponseDto {
    private final String accessToken;
    private final String refreshToken;
}
