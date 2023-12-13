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
    @Column(name = "number_of_appointments_a_year")
    private int numberOfAppointmentsAYear;
    @Column(name = "number_of_appointments_a_month")
    private int numberOfAppointmentsAMonth;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "statistics_massages_a_year",
            joinColumns = @JoinColumn(name = "statistics_id"),
            inverseJoinColumns = @JoinColumn(name = "number_type_id")
    )
    private List<NumberType> numberOfMassagesAYear;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "statistics_massages_a_month",
            joinColumns = @JoinColumn(name = "statistics_id"),
            inverseJoinColumns = @JoinColumn(name = "number_type_id")
    )
    private List<NumberType> numberOfMassagesAMonth;
    private double yearIncome;
    private double averageMonthIncome;
    private String mostWantedMassageYear;
    private String mostWantedMassageMonth;
    private int yearNumber;
    private int monthNumber;
}
