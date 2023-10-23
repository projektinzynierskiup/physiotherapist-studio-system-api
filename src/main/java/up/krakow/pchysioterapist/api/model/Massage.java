package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;

import java.util.Set;

@Entity
@Table(name = "massage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Massage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String massageName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EAppointmentType appointmentType;

    @ManyToMany(mappedBy = "massages")
    Set<Appointment> appointments;

    @OneToOne(mappedBy = "massage")
    private Offer offer;
}
