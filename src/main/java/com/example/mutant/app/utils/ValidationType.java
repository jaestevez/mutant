package com.example.mutant.app.utils;

public enum ValidationType {
    DS_TYPE_MUTANT("mutant"),
    DS_TYPE_HUMAN("no-mutant");

    public final String type;

    private ValidationType(String type) {
        this.type = type;
    }
}
