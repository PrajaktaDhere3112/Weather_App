package com.zeotap.assigntment2.repo;

import com.zeotap.assigntment2.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    
    // Find all weather data for a specific city between a time range
    List<WeatherData> findAllByCityAndDateTimeBetween(String city, LocalDateTime start, LocalDateTime end);
    
    // Find all weather data for a specific city, ordered by datetime (latest first)
    List<WeatherData> findAllByCityOrderByDateTimeDesc(String city);
}
