package whatseating.backend.domain.user.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNameRequestDto {
    @NotNull(message = "변경할 이름을 입력해 주세요.")
    @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로 입력해 주세요.")
    private String name;
}
