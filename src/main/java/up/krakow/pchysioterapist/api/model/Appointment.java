package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "massage_id", nullable = false)
    private Massage massage;

    @ManyToMany
    @JoinTable(
            name = "appointment_massage",
            joinColumns = @JoinColumn(name = "massage_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private Set<Massage> massages;
}
