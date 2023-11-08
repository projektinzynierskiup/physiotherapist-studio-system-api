package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Offer;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    @Query(value = "select o.* from physioterapist.offer as o where o.id = ?1", nativeQuery = true)
    Optional<Offer> findByOfferId(Integer id);
}
