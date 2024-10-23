package com.zeotap.assigntment2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeotap.assigntment2.model.DailyWeatherSummary;
import com.zeotap.assigntment2.model.MaxTemperatureSummary;
import com.zeotap.assigntment2.model.WeatherResponse;
import com.zeotap.assigntment2.repo.DailyWeatherSummaryRepository;
import com.zeotap.assigntment2.service.WeatherService;
// Assuming a response model

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DailyWeatherSummaryRepository dailyWeatherSummaryRepository;

	@GetMapping("/all")
	public ResponseEntity<List<WeatherResponse>> fetchWeatherForAllCities() {
		List<WeatherResponse> weatherResponses = weatherService.fetchWeatherDataForAllCities();
		return ResponseEntity.ok(weatherResponses);
	}

	@GetMapping("/{city}")
	public ResponseEntity<WeatherResponse> fetchWeather(@PathVariable String city) {
		WeatherResponse weatherResponse = weatherService.fetchWeatherData(city);
		return ResponseEntity.ok(weatherResponse);
	}

	
	@GetMapping("/summary/max-temp")
    public List<MaxTemperatureSummary> getMaxTemperatureSummary() {
        List<DailyWeatherSummary> summaries = dailyWeatherSummaryRepository.findAll();
        
        // Group by city and get max temperature
        return summaries.stream()
            .collect(Collectors.groupingBy(DailyWeatherSummary::getCity,
                Collectors.mapping(DailyWeatherSummary::getMaxTemperature, Collectors.toList())))
            .entrySet()
            .stream()
            .map(entry -> new MaxTemperatureSummary(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }
}
