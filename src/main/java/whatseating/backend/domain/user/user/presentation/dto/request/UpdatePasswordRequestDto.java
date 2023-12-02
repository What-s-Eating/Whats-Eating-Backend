package whatseating.backend.domain.user.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해 주세요.")
    private String password;
}
