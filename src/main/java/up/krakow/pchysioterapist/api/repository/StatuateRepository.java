package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Statuate;

@Repository
public interface StatuateRepository extends JpaRepository<Statuate, Integer> {
}
