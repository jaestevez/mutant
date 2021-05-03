package com.example.mutant.app.controller;

import com.example.mutant.app.dto.StatsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
    @GetMapping("/stats")
    public ResponseEntity getStats() {
        StatsDTO stat = new StatsDTO(40, 100, 0.40f);
        return ResponseEntity.ok(stat);
    }
}
