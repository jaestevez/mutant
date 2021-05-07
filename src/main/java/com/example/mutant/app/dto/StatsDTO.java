package com.example.mutant.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDTO {
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;
    @JsonProperty("count_human_dna")
    private long countHumanDna;
    private float ratio;

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
