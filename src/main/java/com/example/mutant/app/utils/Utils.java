package com.example.mutant.app.utils;

import java.util.Arrays;
import java.util.Optional;

public class Utils {
    /**
     *
     * @param dna
     * @return
     */
    public static boolean isValidArrayDimension(String[] dna){
        if(dna == null || dna.length == 0)
            return false;
        int initialSize = dna[0].length();
        Optional<Integer> element = Arrays.stream(dna).map((String item)-> item.length())
                .filter(item -> item != initialSize)
                .findFirst();
        return element.isEmpty();
    }
}
