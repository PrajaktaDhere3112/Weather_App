package com.zeotap.assigntment2.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "weather_data")
@Data
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String weatherCondition;
    private double temperature; // Celsius
    private double feelsLike; // Celsius
    private double humidity; // Percentage (e.g., 45.0 for 45%)
    private double windSpeed; 
    private LocalDateTime dateTime;
    

    // Getters and Setters
    // Constructors
}
