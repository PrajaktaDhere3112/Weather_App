package com.zeotap.assigntment2.service;

import com.zeotap.assigntment2.model.WeatherData;
import com.zeotap.assigntment2.model.WeatherResponse;
import com.zeotap.assigntment2.repo.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    private final String API_KEY = "66cbe9e6e0732f734ef76a4ec7a20f62"; // Replace with your OpenWeatherMap API key
    private final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid=" + API_KEY + "&units=metric";
    private final List<String> cities = Arrays.asList("Delhi", "Chennai", "Bangalore", "Hyderabad");

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private AlertService alertService;

    // Fetch weather data for all cities every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void fetchWeatherDataForCities() {
        for (String city : cities) {
            fetchWeatherData(city);
        }
    }
    
    public List<WeatherResponse> fetchWeatherDataForAllCities() {
        List<WeatherResponse> responses = new ArrayList<>();
        for (String city : cities) {
            WeatherResponse response = fetchWeatherData(city);
            responses.add(response);
        }
        return responses;
    }

    public WeatherResponse fetchWeatherData(String city) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(API_URL, WeatherResponse.class, city);

        // Save weather data to the database
        saveWeatherData(city, response);

        return response;
    }

    private void saveWeatherData(String city, WeatherResponse response) {
        WeatherData weatherData = new WeatherData();
        weatherData.setCity(city); // Set the city name directly
        weatherData.setTemperature(response.getMain().getTemp());
        weatherData.setFeelsLike(response.getMain().getFeels_like());
        weatherData.setWeatherCondition(response.getWeather()[0].getMain());
        weatherData.setDateTime(LocalDateTime.now());

        // Set humidity and wind speed from the response
        weatherData.setHumidity(response.getMain().getHumidity());
        weatherData.setWindSpeed(response.getWind().getSpeed());

        weatherDataRepository.save(weatherData);
        alertService.checkForAlerts(weatherData);
    }
}
