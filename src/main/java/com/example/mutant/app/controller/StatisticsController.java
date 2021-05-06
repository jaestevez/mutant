package com.example.mutant.app.controller;

import com.example.mutant.app.dto.StatsDTO;
import com.example.mutant.app.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/stats")
    public ResponseEntity getStats() {
        StatsDTO stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
