package up.krakow.pchysioterapist.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.krakow.pchysioterapist.api.dto.StatisticsDTO;
import up.krakow.pchysioterapist.api.mapper.StatisticsMapper;
import up.krakow.pchysioterapist.api.service.StatisticsService;
import up.krakow.pchysioterapist.api.utils.ControllerEndpoints;

import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping(value = ControllerEndpoints.MOD + "/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    private final StatisticsMapper statisticsMapper;

    @GetMapping
    ResponseEntity<StatisticsDTO> generateStatistics(@RequestParam("year")Integer year, @RequestParam("month")Integer month) {
        return ResponseEntity.ok(statisticsMapper.mapToStatisticsDTO(statisticsService.generateStatistics(Year.of(year), Month.of(month))));
    }
}
