package com.example.mutant.app.model;

import com.google.cloud.Timestamp;

public class Mutant {
    private String dna;
    private boolean result;
    private Timestamp created;

    public Mutant(){

    }
    public Mutant(String dna, boolean result, Timestamp created) {
        this.dna = dna;
        this.result = result;
        this.created = created;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Mutant{" +
                "dna='" + dna + '\'' +
                ", result=" + result +
                ", created=" + created +
                '}';
    }
}
