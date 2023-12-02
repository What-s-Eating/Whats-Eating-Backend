package whatseating.backend.domain.restaurants.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.restaurants.domain.Restaurants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, UUID> {
    Optional<Restaurants> findById(UUID id);

    List<Restaurants> findNearbyRestaurants(@Param("latitude") String latitude,
                                            @Param("longitude") String longitude);
}
