package up.krakow.pchysioterapist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "statistics")
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numberOfAppointmentsAYear;
    private int numberOfAppointmentsAMonth;
    @ManyToMany
    private List<NumberType> numberOfMassagesAYear;
    @ManyToMany
    private List<NumberType> numberOfMassagesAMonth;
    private double yearIncome;
    private double averageMonthIncome;
    private String mostWantedMassageYear;
    private String mostWantedMassageMonth;
}
