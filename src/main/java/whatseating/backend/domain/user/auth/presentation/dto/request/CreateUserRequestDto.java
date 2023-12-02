package whatseating.backend.domain.user.auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.enums.Provider;
import whatseating.backend.domain.user.user.domain.enums.Role;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {
    @NotNull(message = "이름을 입력해 주세요.")
    private String name;

    @Email
    @NotNull(message = "이메일을 입력해 주세요.")
    private String email;

    @NotNull(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 8~20 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    public User toEntity(String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.USER)
                .provider(Provider.EMAIL)
                .build();
    }
}
