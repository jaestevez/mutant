package com.example.mutant.app.component;

import com.example.mutant.app.exception.MutantException;
import com.example.mutant.app.exception.UnsupportedLetter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ValidationMutant {
    List<String> letters = Arrays.asList("A", "T", "C", "G");
    int SKIP_VERTICAL_LINES = 3;
    int LIMIT_OCCURRENCIES = 2;
    int NUMBER_EQUAL_LETTERS = 4;

    default boolean isValidInput(String [][] matrix){
        Optional<String> element = Arrays.stream(matrix).flatMap(Arrays::stream)
                .filter(item -> !letters.contains(item))
                .findFirst();
        return element.isEmpty();
    }
    int validateHorizontalVertical(String [][] matrix, int occurrencies) throws MutantException;
    int validateDiagonal(String [][] matrix, int occurrencies) throws MutantException;
    int validateDiagonalInverted(String [][] matrix, int occurrencies) throws MutantException;
    boolean isMutant(String [] dna) throws UnsupportedLetter;
}
