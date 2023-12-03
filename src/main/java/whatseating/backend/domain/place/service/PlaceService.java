package whatseating.backend.domain.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.place.domain.Review;
import whatseating.backend.domain.place.domain.Place;
import whatseating.backend.domain.place.domain.repository.ReviewRepository;
import whatseating.backend.domain.place.domain.repository.PlaceRepository;
import whatseating.backend.domain.place.exception.DuplicatedReviewException;
import whatseating.backend.domain.place.exception.PlaceNotFoundException;
import whatseating.backend.domain.place.exception.ReviewNotFoundException;
import whatseating.backend.domain.place.exception.UnauthenticatedException;
import whatseating.backend.domain.place.presentation.dto.request.ReviewRequestDto;
import whatseating.backend.domain.place.presentation.dto.response.ReviewResponseDto;
import whatseating.backend.domain.place.presentation.dto.response.PlaceResponseDto;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.repository.UserRepository;
import whatseating.backend.domain.user.user.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PlaceResponseDto> getNearbyPlace(String x, String y) {
        List<Place> nearbyPlace = placeRepository.findByXAndY(x, y);

        // 근처 음식점이 없을 경우
        if (nearbyPlace.isEmpty()) {
            throw PlaceNotFoundException.EXCEPTION;
        }

        return PlaceResponseDto.of(nearbyPlace);
    }

    @Transactional(readOnly = true)
    public PlaceResponseDto getPlaceInfo(String id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> PlaceNotFoundException.EXCEPTION);

        return PlaceResponseDto.of(place);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getReviews(String id, int page) {
        List<Review> reviewsList = reviewRepository.findReviewByPlaceId(id);

        // 리뷰가 없을 경우
        if (reviewsList.isEmpty()) {
            return ReviewResponseDto.of(new ArrayList<>());
        }

        // 페이징
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, reviewsList.size());

        List<Review> pageReviewsList = reviewsList.subList(start, end);

        return ReviewResponseDto.of(pageReviewsList);
    }

    @Transactional
    public void addReviews(ReviewRequestDto dto, String id, UUID userId) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> PlaceNotFoundException.EXCEPTION);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 리뷰 중복 확인
        if (reviewRepository.findReviewByUserIdAndPlaceId(userId, id).isPresent()) {
            throw DuplicatedReviewException.EXCEPTION;
        }

        Review review = Review.builder()
                .user(user)
                .place(place)
                .star(dto.getStar())
                .content(dto.getContent())
                .build();

        reviewRepository.save(review);
    }

    @Transactional
    public void updateReviews(ReviewRequestDto dto, String id, String review_id, UUID userId) {
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        // 리뷰가 해당 음식점에 속해있는지 확인
        if (!review.getPlace().getId().equals(id)) {
            throw ReviewNotFoundException.EXCEPTION;
        }

        if (!review.getUser().getId().equals(userId)) {
            throw UnauthenticatedException.EXCEPTION;
        }

        review.updateReviews(dto.getContent(), dto.getStar());
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReviews(String id, String review_id, UUID userId) {
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        // 리뷰가 해당 음식점에 속해있는지 확인
        if (!review.getPlace().getId().equals(id)) {
            throw ReviewNotFoundException.EXCEPTION;
        }

        if (!review.getUser().getId().equals(userId)) {
            throw UnauthenticatedException.EXCEPTION;
        }

        reviewRepository.deleteById(review_id);
    }
}
