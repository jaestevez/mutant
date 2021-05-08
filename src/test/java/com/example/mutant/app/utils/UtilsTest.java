package com.example.mutant.app.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void testValidArrayDimension6Rows() {
        final String [] dna = {
                "ATGCGA",
                "CTGAGC",
                "TACGGA",
                "ATGCAG",
                "CGTATA",
                "TCATTC"
        };
        assertTrue(Utils.isValidArrayDimension(dna));
    }

    @Test
    public void testValidArrayDimension7Rows() {
        final String [] dna = {
                "ATGCGAA",
                "CTGAGCA",
                "TACGGAA",
                "ATGCAGA",
                "CGTATAA",
                "TCATTCA",
                "TCATTCA"
        };
        assertTrue(Utils.isValidArrayDimension(dna));
    }

    @Test
    public void testNotValidArrayDimension() {
        final String [] dna = {
                "ATGCGAA",
                "CTGAGCA",
                "TACGGAA",
                "ATGCAGA",
                "CGTATAA",
                "TCATTC"
        };
        assertFalse(Utils.isValidArrayDimension(dna));
    }

    @Test
    public void testNotValidArrayDimensionMissinARow() {
        final String [] dna = {
                "ATGCGAA",
                "CTGAGCA",
                "TACGGAA",
                "ATGCAGA",
                "CGTATAA",
                "TCATTCA"
        };
        assertFalse(Utils.isValidArrayDimension(dna));
    }

    @Test
    public void testArrayDimensionEmpty() {
        final String [] dna = {};
        assertFalse(Utils.isValidArrayDimension(dna));
    }
}
