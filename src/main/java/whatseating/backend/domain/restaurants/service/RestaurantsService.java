package whatseating.backend.domain.restaurants.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.restaurants.domain.Review;
import whatseating.backend.domain.restaurants.domain.Restaurants;
import whatseating.backend.domain.restaurants.domain.repository.ReviewRepository;
import whatseating.backend.domain.restaurants.domain.repository.RestaurantsRepository;
import whatseating.backend.domain.restaurants.exception.DuplicatedReviewException;
import whatseating.backend.domain.restaurants.exception.RestaurantsNotFoundException;
import whatseating.backend.domain.restaurants.exception.ReviewNotFoundException;
import whatseating.backend.domain.restaurants.presentation.dto.request.ReviewRequestDto;
import whatseating.backend.domain.restaurants.presentation.dto.response.ReviewResponseDto;
import whatseating.backend.domain.restaurants.presentation.dto.response.RestaurantsResponseDto;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.repository.UserRepository;
import whatseating.backend.domain.user.user.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantsService {
    private final RestaurantsRepository restaurantsRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RestaurantsResponseDto> getNearbyRestaurants(String latitude, String longitude) {
        List<Restaurants> nearbyRestaurants = restaurantsRepository.findByLatitudeAndLongitude(latitude, longitude);

        // 근처 음식점이 없을 경우
        if (nearbyRestaurants.isEmpty()) {
            throw RestaurantsNotFoundException.EXCEPTION;
        }

        return RestaurantsResponseDto.of(nearbyRestaurants);
    }

    @Transactional(readOnly = true)
    public RestaurantsResponseDto getRestaurantInfo(UUID id) {
        Restaurants restaurants = restaurantsRepository.findById(id)
                .orElseThrow(() -> RestaurantsNotFoundException.EXCEPTION);

        return RestaurantsResponseDto.of(restaurants);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getReviews(UUID id, int page) {
        List<Review> reviewsList = reviewRepository.findReviewByRestaurantsId(id);

        // 리뷰가 없을 경우
        if (reviewsList.isEmpty()) {
            throw ReviewNotFoundException.EXCEPTION;
        }

        // 페이징
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, reviewsList.size());

        List<Review> pageReviewsList = reviewsList.subList(start, end);

        return ReviewResponseDto.of(pageReviewsList);
    }

    @Transactional
    public void addReviews(ReviewRequestDto dto, UUID id) {
        Restaurants restaurants = restaurantsRepository.findById(id)
                .orElseThrow(() -> RestaurantsNotFoundException.EXCEPTION);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 리뷰 중복 확인
        if (reviewRepository.findReviewByUserIdAndRestaurantsId(dto.getUserId(), id).isPresent()) {
            throw DuplicatedReviewException.EXCEPTION;
        }

        Review review = Review.builder()
                .user(user)
                .restaurants(restaurants)
                .star(dto.getStar())
                .content(dto.getContent())
                .build();

        reviewRepository.save(review);
    }

    @Transactional
    public void updateReviews(ReviewRequestDto dto, UUID id, UUID review_id) {
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        // 리뷰가 해당 음식점에 속해있는지 확인
        if (!review.getRestaurants().getId().equals(id)) {
            throw ReviewNotFoundException.EXCEPTION;
        }

        review.updateReviews(dto.getContent(), dto.getStar());
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReviews(UUID id, UUID review_id) {
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        // 리뷰가 해당 음식점에 속해있는지 확인
        if (!review.getRestaurants().getId().equals(id)) {
            throw ReviewNotFoundException.EXCEPTION;
        }

        reviewRepository.deleteById(review_id);
    }
}
