package whatseating.backend.domain.place.presentation.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {

    @NotNull(message = "별점을 입력해주세요.")
    @Size(min = 1, max = 5, message = "별점은 1~5 사이만 가능합니다.")
    private String star;

    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    @Size(max = 200, message = "리뷰 내용은 200자 이내로 작성해주세요.")
    private String content;
}
