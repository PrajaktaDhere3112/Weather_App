// File: DailyWeatherSummary.java
package com.zeotap.assigntment2.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "daily_weather_summary")
public class DailyWeatherSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private double averageTemperature;
    private double maxTemperature;
    private double minTemperature;
    private String dominantWeatherCondition;
    private LocalDate date;

    // Getters and Setters
    // Constructors
}
