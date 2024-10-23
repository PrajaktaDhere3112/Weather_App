package com.zeotap.assigntment2.service;

import com.zeotap.assigntment2.model.DailyWeatherSummary;
import com.zeotap.assigntment2.model.WeatherData;
import com.zeotap.assigntment2.repo.DailyWeatherSummaryRepository;
import com.zeotap.assigntment2.repo.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class DailyWeatherSummaryService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private DailyWeatherSummaryRepository dailyWeatherSummaryRepository;

    private static final List<String> VALID_CITIES = Arrays.asList("Delhi", "Chennai", "Bangalore", "Hyderabad");

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression means the method will run every day at midnight (00:00)
    public void generateDailySummary() {
        for (String city : VALID_CITIES) {
            LocalDate today = LocalDate.now();
            List<WeatherData> dataForToday = weatherDataRepository.findAllByCityAndDateTimeBetween(
                city,
                today.atStartOfDay(),
                today.atTime(23, 59, 59)
            );

            if (dataForToday.isEmpty()) {
                System.out.println("No weather data available for " + city + " today.");
                continue; // Skip to the next city if no data is found
            }

            double averageTemp = dataForToday.stream().mapToDouble(WeatherData::getTemperature).average().orElse(0);
            double maxTemp = dataForToday.stream().mapToDouble(WeatherData::getTemperature).max().orElse(0);
            double minTemp = dataForToday.stream().mapToDouble(WeatherData::getTemperature).min().orElse(0);
            String dominantWeatherCondition = calculateDominantWeatherCondition(dataForToday);

            DailyWeatherSummary summary = new DailyWeatherSummary();
            summary.setCity(city);
            summary.setAverageTemperature(averageTemp);
            summary.setMaxTemperature(maxTemp);
            summary.setMinTemperature(minTemp);
            summary.setDominantWeatherCondition(dominantWeatherCondition);

            dailyWeatherSummaryRepository.save(summary);
            System.out.println("Daily summary generated for " + city + ": " + summary);
        }
    }

    private String calculateDominantWeatherCondition(List<WeatherData> data) {
        // Logic to determine the dominant weather condition based on frequency
        return data.stream()
            .map(WeatherData::getWeatherCondition)
            .reduce((first, second) -> first) // This should be replaced with actual logic
            .orElse("Unknown");
    }
}
