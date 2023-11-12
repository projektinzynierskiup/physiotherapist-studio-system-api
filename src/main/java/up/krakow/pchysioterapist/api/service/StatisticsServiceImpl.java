package up.krakow.pchysioterapist.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import up.krakow.pchysioterapist.api.model.NumberType;
import up.krakow.pchysioterapist.api.model.Statistics;
import up.krakow.pchysioterapist.api.model.enums.EAppointmentType;
import up.krakow.pchysioterapist.api.repository.StatisticsRepository;

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
        LocalDateTime startDate = LocalDateTime.from(year.atDay(1));
        LocalDateTime endDate = LocalDateTime.from(year.atDay(year.length()));
        LocalDateTime startMonth = LocalDateTime.from(year.atMonth(month).atDay(1));
        LocalDateTime endMonth = LocalDateTime.from(year.atMonth(month).atDay(month.maxLength()));
        Statistics statistics;
        if (statisticsRepository.findByYearAndMonth(year.getValue(), month.getValue()) != null)
            statistics = statisticsRepository.findByYearAndMonth(year.getValue(), month.getValue());
        else statistics = new Statistics();
        statistics.setYearNumber(year.getValue());
        statistics.setMonthNumber(month.getValue());
        int numberOfAppointmentsAYear = statisticsRepository.getNumberOfAppointments(startDate, endDate);
        statistics.setNumberOfAppointmentsAYear(numberOfAppointmentsAYear);
        int numberOfAppointmentsAMonth = statisticsRepository.getNumberOfAppointments(startMonth, endMonth);
        statistics.setNumberOfAppointmentsAMonth(numberOfAppointmentsAMonth);
        double yearIncome = statisticsRepository.getIncome(startDate, endDate);
        statistics.setYearIncome(yearIncome);
        double monthIncome = statisticsRepository.getIncome(startMonth, endMonth);
        statistics.setAverageMonthIncome(monthIncome);
        statisticsRepository.save(statistics);
        List<NumberType> numberOfMassagesAYear = getNumberOfMassages(startDate, endDate);
        statistics.setNumberOfMassagesAYear(numberOfMassagesAYear);
        List<NumberType> numberOfMassagesMonth = getNumberOfMassages(startMonth, endMonth);
        statistics.setNumberOfMassagesAMonth(numberOfMassagesMonth);
        statistics.setMostWantedMassageYear(getMostWantedMassage(numberOfMassagesAYear));
        statistics.setMostWantedMassageMonth(getMostWantedMassage(numberOfMassagesMonth));
        return statistics;
    }

    public List<NumberType> getNumberOfMassages(LocalDateTime start, LocalDateTime end) {
        List<NumberType> numberTypes = new ArrayList<>();
        List<EAppointmentType> types = List.of(EAppointmentType.values());
        for (EAppointmentType type: types){
            NumberType numberType = new NumberType();
            numberType.setNumberOf(statisticsRepository.getNumberOfMassagesByType(start, end, type.toString()));
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
