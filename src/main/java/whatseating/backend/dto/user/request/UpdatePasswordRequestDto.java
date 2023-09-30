package whatseating.backend.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequestDto {
    @NotNull(message = "현재 비밀번호를 입력해 주세요.")
    private String currentPassword;

    @NotNull(message = "변경할 비밀번호를 입력해 주세요.")
    private String password;
}
