package up.krakow.pchysioterapist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.krakow.pchysioterapist.api.model.NumberType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDTO {

    private Integer id;
    private int numberOfAppointmentsAYear;
    private int numberOfAppointmentsAMonth;
    private List<NumberType> numberOfMassagesAYear;
    private List<NumberType> numberOfMassagesAMonth;
    private double yearIncome;
    private double averageMonthIncome;
    private String mostWantedMassageYear;
    private String mostWantedMassageMonth;
    private int yearNumber;
    private int monthNumber;
}
