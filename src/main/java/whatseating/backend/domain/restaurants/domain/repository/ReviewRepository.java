package whatseating.backend.domain.restaurants.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.restaurants.domain.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findReviewByRestaurantsId(UUID id);

    Optional<Review> findReviewByUserIdAndRestaurantsId(UUID userId, UUID restaurantsId);
}