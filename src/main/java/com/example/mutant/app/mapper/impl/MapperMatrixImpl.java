package com.example.mutant.app.mapper.impl;

import com.example.mutant.app.mapper.MapperMatrix;
import org.springframework.stereotype.Component;

@Component()
public class MapperMatrixImpl implements MapperMatrix {
    /**
     * Convierte la entrada(arreglo) en una matrix
     * @param dna
     * @return
     */
    public String[][] dnaToMatrix(String[] dna){
        String [][] dnaMatrix = new String[dna.length][];
        for(int i=0;i<dna.length;i++){
            dnaMatrix[i] = dna[i].split("");
        }
        return dnaMatrix;
    }
}
