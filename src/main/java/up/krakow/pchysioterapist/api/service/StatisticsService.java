package up.krakow.pchysioterapist.api.service;

import up.krakow.pchysioterapist.api.model.Statistics;

import java.time.Month;
import java.time.Year;

public interface StatisticsService {
    Statistics generateStatistics(Year year, Month month);
}
