package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.*;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.util.Set;

@Entity
@Table(name = "massage")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Massage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "massage_name")
    private String massageName;
    private String description;
    @Column(name = "appointment_type")
    @Enumerated(EnumType.STRING)
    private EAppointmentType appointmentType;

    @ManyToMany(mappedBy = "massages")
    Set<Appointment> appointments;

    @OneToOne(mappedBy = "massage")
    private Offer offer;
}
