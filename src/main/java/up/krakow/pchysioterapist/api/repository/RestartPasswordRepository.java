package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.RestartPassword;

import java.util.Optional;

@Repository
public interface RestartPasswordRepository extends JpaRepository<RestartPassword, Integer> {
    Optional<RestartPassword> findByUuid(String uuid);
}
