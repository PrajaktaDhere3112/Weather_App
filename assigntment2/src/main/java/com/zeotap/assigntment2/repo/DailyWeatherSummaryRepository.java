package com.zeotap.assigntment2.repo;

import com.zeotap.assigntment2.model.DailyWeatherSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyWeatherSummaryRepository extends JpaRepository<DailyWeatherSummary, Long> {
    
    // Find the daily summary for a specific city on a specific date
    DailyWeatherSummary findByCityAndDate(String city, LocalDate date);
    List<DailyWeatherSummary> findAllByOrderByDate();
}
