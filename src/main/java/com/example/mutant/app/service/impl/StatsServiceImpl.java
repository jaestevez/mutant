package com.example.mutant.app.service.impl;

import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.dto.StatsDTO;
import com.example.mutant.app.service.StatsService;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsDAO statsDAO;

    /**
     * Consulta estadisticas de las validaciones realizadas
     * @return
     */
    @Override
    public StatsDTO getStats() {
        StatsDTO stats = new StatsDTO();
        Entity entity = statsDAO.getStats();
        if(entity != null){
            Map<String, Value<?>> props = entity.getProperties();
            stats.setCountMutantDna((long) props.get("mutant").get());
            stats.setCountHumanDna((long)props.get("no-mutant").get());
            stats.setRatio((float)stats.getCountMutantDna()/(stats.getCountMutantDna() + stats.getCountHumanDna()));
        }
        return stats;
    }
}
