package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Newsletter;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Integer> {

    Newsletter findByUserEmail(String userEmail);
}