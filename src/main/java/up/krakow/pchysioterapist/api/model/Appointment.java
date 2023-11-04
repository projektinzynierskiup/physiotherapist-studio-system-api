package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentStatus;

import java.time.LocalDateTime;
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
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private String urlKey;

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
