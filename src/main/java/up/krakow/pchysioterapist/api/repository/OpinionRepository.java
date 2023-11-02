package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
    Page<Opinion> findAll(Pageable pageable);

}
