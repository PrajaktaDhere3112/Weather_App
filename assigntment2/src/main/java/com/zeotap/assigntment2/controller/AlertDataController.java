package com.zeotap.assigntment2.controller;

import com.zeotap.assigntment2.model.AlertData;
import com.zeotap.assigntment2.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertDataController {

    @Autowired
    private AlertService alertService;

    // Get alerts for a specific city
    @GetMapping("/{city}")
    public ResponseEntity<List<AlertData>> getAlertsByCity(@PathVariable String city) {
        List<AlertData> alerts = alertService.getTriggeredAlerts(city);
        return ResponseEntity.ok(alerts);
    }

    // Get all alerts
    @GetMapping
    public ResponseEntity<List<AlertData>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
