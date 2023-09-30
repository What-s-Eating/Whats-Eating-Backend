package whatseating.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatseating.backend.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
