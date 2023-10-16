package whatseating.backend.domain.user.auth.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotNull(message = "이메일을 입력해 주세요.")
    private String email;

    @NotNull(message = "비밀번호를 입력해 주세요.")
    private String password;
}
