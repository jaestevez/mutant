package com.example.mutant.app.dto;

import java.util.Arrays;

public class MutantDTO {
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "MatrixDTO{" +
                "dna=" + Arrays.toString(dna) +
                '}';
    }
}
