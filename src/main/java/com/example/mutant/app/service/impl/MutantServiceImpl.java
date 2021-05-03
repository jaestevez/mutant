package com.example.mutant.app.service.impl;

import com.example.mutant.app.component.ValidationMutant;
import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import com.example.mutant.app.service.MutantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {
    private static Logger log = LogManager.getLogger(MutantServiceImpl.class);
    @Autowired
    private ValidationMutant validation;

    /**
     *
     * @param mutant
     * @return
     * @throws UnsupportedLetter
     */
    public boolean validateMutant(MutantDTO mutant) throws UnsupportedLetter, UnsupportedDimensionArray {
        log.debug("input: ".concat(mutant.toString()));
        return validation.isMutant(mutant.getDna());
    }
}
