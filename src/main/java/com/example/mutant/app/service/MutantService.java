package com.example.mutant.app.service;

import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.exception.UnsupportedLetter;

public interface MutantService {
    boolean validateMutant(MutantDTO matrix) throws UnsupportedLetter;
}
