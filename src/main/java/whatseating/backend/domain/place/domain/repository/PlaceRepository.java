package whatseating.backend.domain.place.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.place.domain.Place;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
    Optional<Place> findById(UUID id);

    List<Place> findByXAndY(@Param("x") String x,
                            @Param("y") String y);
}
