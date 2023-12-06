package whatseating.backend.domain.detail.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.detail.domain.Detail;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, String> {
    @Override
    Optional<Detail> findById(String id);
}
