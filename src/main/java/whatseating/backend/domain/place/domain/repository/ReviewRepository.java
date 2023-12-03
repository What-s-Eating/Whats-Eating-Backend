package whatseating.backend.domain.place.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.place.domain.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findReviewByPlaceId(UUID id);

    Optional<Review> findReviewByUserIdAndPlaceId(UUID userId, UUID placeId);
}