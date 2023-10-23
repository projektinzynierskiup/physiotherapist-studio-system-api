package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Offer;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
