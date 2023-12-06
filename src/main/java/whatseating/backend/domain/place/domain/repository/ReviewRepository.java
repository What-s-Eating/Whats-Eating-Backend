package whatseating.backend.domain.place.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.place.domain.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findReviewByUserIdAndPlaceId(String userId, String placeId);

    List<Review> findReviewByPlaceIdOrderByCreatedAtDesc(String id);
}