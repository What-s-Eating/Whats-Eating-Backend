package whatseating.backend.domain.place.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import whatseating.backend.domain.place.domain.Review;
import whatseating.backend.domain.place.exception.ReviewNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReviewResponseDto {
    private String id;
    private UUID userId;
    private String userName;
    private String imgUrl;
    private String star;
    private String content;

    public static List<ReviewResponseDto> of(List<Review> reviews) {
        if (reviews == null) throw ReviewNotFoundException.EXCEPTION;

        return reviews.stream()
                .map(review -> ReviewResponseDto.builder()
                        .id(review.getId())
                        .userId(review.getUser().getId())
                        .userName(review.getUser().getName())
                        .imgUrl(review.getUser().getProfileImage())
                        .star(review.getStar())
                        .content(review.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
