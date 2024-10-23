package com.zeotap.assigntment2.controller;

import com.zeotap.assigntment2.model.DailyWeatherSummary;
import com.zeotap.assigntment2.repo.DailyWeatherSummaryRepository;
import com.zeotap.assigntment2.service.DailyWeatherSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/summary")
public class DailyWeatherSummaryController {

    @Autowired
    private DailyWeatherSummaryRepository dailyWeatherSummaryRepository;

    @Autowired
    private DailyWeatherSummaryService dailyWeatherSummaryService;

    @GetMapping
    public ResponseEntity<List<DailyWeatherSummary>> generateAndFetchDailySummaries() {
        // Generate daily summaries
        dailyWeatherSummaryService.generateDailySummary(); // This will calculate and save summaries for all valid cities

        // Fetch and return the daily summaries
        List<DailyWeatherSummary> summaries = dailyWeatherSummaryRepository.findAll();
        return ResponseEntity.ok(summaries);
    }
    
    
}
