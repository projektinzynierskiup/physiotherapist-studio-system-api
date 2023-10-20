package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.OfferPhoto;

import java.util.UUID;

@Repository
public interface OfferPhotoRepository extends JpaRepository<OfferPhoto, Integer> {
}
