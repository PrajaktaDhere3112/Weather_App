package com.zeotap.assigntment2.service;

import com.zeotap.assigntment2.model.AlertData;
import com.zeotap.assigntment2.model.WeatherData;
import com.zeotap.assigntment2.repo.AlertDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertService {

    private static final double TEMPERATURE_THRESHOLD = 35.0; // Temperature threshold for alert
    private static final int CONSECUTIVE_THRESHOLD = 2; // Number of consecutive occurrences to trigger alert

    private final Map<String, Integer> alertCountMap = new HashMap<>(); // Tracks consecutive alerts per city

    @Autowired
    private AlertDataRepository alertDataRepository;

    public void checkForAlerts(WeatherData weatherData) {
        String city = weatherData.getCity();
        double temperature = weatherData.getTemperature();

        // Initialize or reset alert count for city
        alertCountMap.putIfAbsent(city, 0);

        if (temperature > TEMPERATURE_THRESHOLD) {
            alertCountMap.put(city, alertCountMap.get(city) + 1); // Increment the count if temperature exceeds threshold

            // Trigger alert if consecutive count exceeds threshold
            if (alertCountMap.get(city) >= CONSECUTIVE_THRESHOLD) {
                String alertMessage = "Alert: Temperature exceeds " + TEMPERATURE_THRESHOLD + "°C in " + city;
                saveAlert(city, alertMessage);
                System.out.println(alertMessage); // Log the alert
                alertCountMap.put(city, 0); // Reset the count after the alert is triggered
            }
        } else {
            // Reset the alert count if temperature is below the threshold
            alertCountMap.put(city, 0);
        }
    }

    // Save alert data into the database
    private void saveAlert(String city, String message) {
        AlertData alertData = new AlertData();
        alertData.setCity(city);
        alertData.setMessage(message);
        alertData.setDateTime(LocalDateTime.now());
        alertDataRepository.save(alertData);
    }

    // Retrieve triggered alerts for a city
    public List<AlertData> getTriggeredAlerts(String city) {
        return alertDataRepository.findByCity(city);
    }

    // Retrieve all alerts
    public List<AlertData> getAllAlerts() {
        return alertDataRepository.findAll();
    }
}
