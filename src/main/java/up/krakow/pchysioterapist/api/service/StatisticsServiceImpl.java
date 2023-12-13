package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.model.NumberType;
import up.krakow.pchysioterapist.api.model.Statistics;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.repository.StatisticsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService{

    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics generateStatistics(Year year, Month month) {
        LocalDate startDate = LocalDate.from(year.atDay(1));
        LocalDate endDate = LocalDate.from(year.atDay(year.length()));
        LocalDate startMonth = LocalDate.from(year.atMonth(month).atDay(1));
        LocalDate endMonth = LocalDate.from(year.atMonth(month).atDay(month.maxLength()));
        Statistics statistics;
        if (statisticsRepository.findByYearAndMonth(year.getValue(), month.getValue()) != null)
            statistics = statisticsRepository.findByYearAndMonth(year.getValue(), month.getValue());
        else statistics = new Statistics();
        statistics.setYearNumber(year.getValue());
        statistics.setMonthNumber(month.getValue());
        int numberOfAppointmentsAYear = statisticsRepository.getNumberOfAppointments(startDate, endDate).orElse(0);
        statistics.setNumberOfAppointmentsAYear(numberOfAppointmentsAYear);
        int numberOfAppointmentsAMonth = statisticsRepository.getNumberOfAppointments(startMonth, endMonth).orElse(0);
        statistics.setNumberOfAppointmentsAMonth(numberOfAppointmentsAMonth);
        double yearIncome = statisticsRepository.getIncome(startDate, endDate).orElse(0.0);
        statistics.setYearIncome(yearIncome);
        double monthIncome = statisticsRepository.getIncome(startMonth, endMonth).orElse(0.0);
        statistics.setAverageMonthIncome(monthIncome);
        List<NumberType> numberOfMassagesAYear = getNumberOfMassages(startDate, endDate);
        statistics.setNumberOfMassagesAYear(numberOfMassagesAYear);
        List<NumberType> numberOfMassagesMonth = getNumberOfMassages(startMonth, endMonth);
        statistics.setNumberOfMassagesAMonth(numberOfMassagesMonth);
        statistics.setMostWantedMassageYear(getMostWantedMassage(numberOfMassagesAYear));
        statistics.setMostWantedMassageMonth(getMostWantedMassage(numberOfMassagesMonth));
        statisticsRepository.save(statistics);
        return statistics;
    }

    public List<NumberType> getNumberOfMassages(LocalDate start, LocalDate end) {
        List<NumberType> numberTypes = new ArrayList<>();
        List<EAppointmentType> types = List.of(EAppointmentType.values());
        for (EAppointmentType type: types){
            NumberType numberType = new NumberType();
            numberType.setNumberOf(statisticsRepository.getNumberOfMassagesByType(start, end, type.toString()).orElse(0));
            numberType.setTypeOf(type.toString());
            numberTypes.add(numberType);
        }
        return numberTypes;
    }

    public String getMostWantedMassage(List<NumberType> numberTypes) {
        int maxNumber = 0;
        String massageType = "";
        for (NumberType numberType: numberTypes){
            if (numberType.getNumberOf() > maxNumber) {
                maxNumber = numberType.getNumberOf();
                massageType = numberType.getTypeOf();
            }
        }
        return massageType;
    }
}
