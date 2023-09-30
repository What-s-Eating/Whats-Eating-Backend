package whatseating.backend.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatseating.backend.domain.User;
import whatseating.backend.domain.enums.Role;

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
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
