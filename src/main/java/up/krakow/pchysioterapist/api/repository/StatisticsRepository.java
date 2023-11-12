package up.krakow.pchysioterapist.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import up.krakow.pchysioterapist.api.model.Statistics;

import java.time.LocalDateTime;

@Repository
public interface StatisticsRepository  extends JpaRepository<Statistics, Integer> {

    @Query(value = "select a.* from physioterapist.appointment as a where (a.start_date between ?1 and ?2) and a.status = 'FINISHED'", nativeQuery = true)
    int getNumberOfAppointments(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "select sum(o.price) from physioterapist.offer as o join physioterapist.appointment as a on a.massage_id = o.massage_id where a.start_date between ?1 and ?2", nativeQuery = true)
    double getIncome(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "select a.massage_id from physioterapist.appointment as a join physioterapist.massage as m on m.id = a.massage_id where (a.start_date between ?1 and ?2) and m.appointment_type = ?3 and a.status = 'FINISHED'", nativeQuery = true)
    int getNumberOfMassagesByType(LocalDateTime startDate, LocalDateTime endDate, String appointmentType);

    @Query(value = "select s.* from physioterapist.statistics as s where s.year_number = ?1 and s.month_number = ?2", nativeQuery = true)
    Statistics findByYearAndMonth(int year, int mont);
}
