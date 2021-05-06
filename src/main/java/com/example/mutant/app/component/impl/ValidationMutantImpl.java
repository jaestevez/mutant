package com.example.mutant.app.component.impl;

import com.example.mutant.app.component.ValidationMutant;
import com.example.mutant.app.exception.MutantException;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import com.example.mutant.app.mapper.MapperMatrix;
import com.example.mutant.app.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Component
public class ValidationMutantImpl implements ValidationMutant {
    private static Logger log = LogManager.getLogger(ValidationMutantImpl.class);
    @Autowired
    private MapperMatrix mapperMatrix;

    /**
     * Validación de filas y columnas
     * @param matrix
     * @param accumulated
     * @return
     * @throws MutantException
     */
    @Override
    public int validateHorizontalVertical(String [][] matrix, int accumulated) throws MutantException {
        int occurrencies = accumulated;
        HashMap<String, Integer> groupHorizontal = new HashMap<>();
        ArrayList<HashMap<String, Integer>> groupVertical = new ArrayList<>();
        for(int x=0;x<matrix.length; x++){
            for(int y=0;y<matrix[x].length; y++){
                // validacion horizontal
                occurrencies = validateLine(groupHorizontal, x, y, matrix, occurrencies);
                // validacion vertical
                if(x == 0){
                    groupVertical.add(new HashMap<>());
                }
                occurrencies = validateLine(groupVertical.get(y), x, y, matrix, occurrencies);
            }
            groupHorizontal.clear();
        }
        return occurrencies;
    }

    /**
     * Validación de diagonales de derecha superior a izquierda inferior
     * @param matrix
     * @param occurrencies
     * @return
     * @throws MutantException
     */
    @Override
    public int validateDiagonal(String [][] matrix, int occurrencies) throws MutantException{
        int width = matrix.length;
        int height = matrix[0].length;
        HashMap<String, Integer> group = new HashMap<>();
        for (int line = 1 - width+SKIP_VERTICAL_LINES; line < height-SKIP_VERTICAL_LINES; line++) {
            for (int x = -min(0, line), y = max(0, line); x < width && y < height; x++, y++) {
                occurrencies = validateLine(group, x, y, matrix, occurrencies);
            }
            group.clear();
        }
        return occurrencies;
    }

    /**
     * Validación de diagonales de izquierda superior a derecha inferior
     * @param matrix
     * @param occurrencies
     * @return
     * @throws MutantException
     */
    @Override
    public int validateDiagonalInverted(String [][] matrix, int occurrencies) throws MutantException{
        int width = matrix.length;
        int height = matrix[0].length;
        HashMap<String, Integer> group = new HashMap<>();
        for (int line = SKIP_VERTICAL_LINES + 1;line <= (width + height - SKIP_VERTICAL_LINES); line++) {
            int columnIndexStart = max(0, line - width);
            int numElements = min(min(line, (height - columnIndexStart)), width);
            for (int x = columnIndexStart, y = min(width, line) - 1; x < numElements + columnIndexStart; x++, y--) {
                occurrencies = validateLine(group, x, y, matrix, occurrencies);
            }
            group.clear();
        }
        return occurrencies;
    }

    /**
     * Valida si un dna tiene hasta dos conincidencias
     * @param dna
     * @return
     * @throws UnsupportedLetter
     */
    @Override
    public boolean isMutant(final String[] dna) throws UnsupportedLetter {
        int occurrencies = 0;
        if(!Utils.isValidArrayDimension(dna)){
            throw new UnsupportedDimensionArray();
        }
        final String [][] matrix = mapperMatrix.dnaToMatrix(dna);
        if (!isValidInput(matrix)) {
            log.debug("character failed validation");
            throw new UnsupportedLetter();
        }
        try {
            occurrencies = validateHorizontalVertical(matrix, occurrencies);
            occurrencies = validateDiagonal(matrix, occurrencies);
            validateDiagonalInverted(matrix, occurrencies);
        }catch (MutantException e){
            return true;
        }
        return false;
    }

    /**
     * Validación por linea de las coincidencias
     * @param group
     * @param x
     * @param y
     * @param matrix
     * @param occurrencies
     * @return
     * @throws MutantException
     */
    private int validateLine(HashMap<String, Integer> group, int x, int y, String [][] matrix, int occurrencies) throws MutantException {
        log.debug("[".concat(Integer.toString(y)).concat(",").concat(Integer.toString(x)).concat(", ").concat(matrix[y][x]).concat("]"));
        int value = 1;
        if(group.containsKey(matrix[y][x])){
            value += group.get(matrix[y][x]);
            if(value >= NUMBER_EQUAL_LETTERS){
                occurrencies++;
                log.debug("[Occurrence ".concat(Integer.toString(occurrencies)).concat("]"));
                if(occurrencies >= LIMIT_OCCURRENCIES){
                    throw new MutantException();
                }
            }
        }else {
            group.clear();
        }
        group.put(matrix[y][x], value);
        return occurrencies;
    }

}
