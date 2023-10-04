package whatseating.backend.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNameRequestDto {
    @NotNull(message = "변경할 이름을 입력해 주세요.")
    private String name;
}
