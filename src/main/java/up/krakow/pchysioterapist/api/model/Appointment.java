package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.time.LocalDate;
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
    private Integer userId;
    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Users users;

    @ManyToMany
    @JoinTable(
            name = "appointment_massage",
            joinColumns = @JoinColumn(name = "massage_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private Set<Massage> massages;
}
