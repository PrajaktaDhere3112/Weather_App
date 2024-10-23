package com.zeotap.assigntment2.repo;

import com.zeotap.assigntment2.model.AlertData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertDataRepository extends JpaRepository<AlertData, Long> {
    List<AlertData> findByCity(String city);
}
