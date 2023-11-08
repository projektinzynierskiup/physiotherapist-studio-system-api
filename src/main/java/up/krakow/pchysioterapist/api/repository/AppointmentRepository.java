package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query(value = "select a.* from physioterapist.appointment as a where a.id = ?1", nativeQuery = true)
    Optional<Appointment> findByAppointmentId(Integer id);
    List<Appointment> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findAllByStatus(String status);
}
