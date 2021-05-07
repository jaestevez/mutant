package com.example.mutant.app.service.impl;

import com.example.mutant.app.component.ValidationMutant;
import com.example.mutant.app.dao.MutantDAO;
import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import com.example.mutant.app.model.Mutant;
import com.example.mutant.app.service.MutantService;
import com.example.mutant.app.utils.ValidationType;
import com.google.cloud.Timestamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MutantServiceImpl implements MutantService {
    private static Logger log = LogManager.getLogger(MutantServiceImpl.class);
    @Autowired
    private ValidationMutant validation;
    @Autowired
    private MutantDAO mutantDAO;
    @Autowired
    private StatsDAO statsDAO;

    /**
     * Validación de dna recibido y almacena el dna con su resultado
     * @param mutant
     * @return
     * @throws UnsupportedLetter
     */
    public boolean validateMutant(MutantDTO mutant) throws UnsupportedLetter, UnsupportedDimensionArray {
        boolean result = validation.isMutant(mutant.getDna());
        Mutant mutantModel = new Mutant();
        mutantModel.setDna(Arrays.toString(mutant.getDna()));
        mutantModel.setResult(result);
        mutantModel.setCreated(Timestamp.now());
        log.info("validation: ".concat(mutantModel.toString()));
        mutantDAO.saveMutant(mutantModel);
        statsDAO.insertOrUpdateStats(result ? ValidationType.DS_TYPE_MUTANT.type : ValidationType.DS_TYPE_HUMAN.type);
        return result;
    }
}
