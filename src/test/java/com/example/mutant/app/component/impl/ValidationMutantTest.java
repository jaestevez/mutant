package com.example.mutant.app.component.impl;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.component.ValidationMutant;
import com.example.mutant.app.dao.impl.DataStoreFactory;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
public class ValidationMutantTest {
    @Autowired
    private ValidationMutant validation;
    @MockBean
    private DataStoreFactory dataStoreFactory;

    @Test
    public void testMutantHorizontalVerticalMatch() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CAAAAC",
                "TTGTGT",
                "ATAATC",
                "CTTCTA",
                "TTACTG"
        };
        assertTrue(validation.isMutant(dna));
    }

    @Test
    public void testMutantDiagonalMatch() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CATTGC",
                "TTATGT",
                "AGAATC",
                "CCTCTA",
                "TCACTG"
        };
        assertTrue(validation.isMutant(dna));
    }

    @Test
    public void testMutantDiagonalInvertedMatch() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CAGGGC",
                "TACGGA",
                "ATGCAG",
                "CGTATA",
                "TCATTC"
        };
        assertTrue(validation.isMutant(dna));
    }

    @Test
    public void testMutantDiagonalAndDiagonalInvertedMatch() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CTGAGC",
                "TACGGA",
                "ATGCAG",
                "CGTATA",
                "TCATTC"
        };
        assertTrue(validation.isMutant(dna));
    }
    @Test
    public void testNoMutantAPattern() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CTGAGC",
                "TACGGA",
                "ATGCAG",
                "CTTATA",
                "TCCTTC"
        };
        assertFalse(validation.isMutant(dna));
    }

    @Test
    public void testNoMutantNoPatterns() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CTGAGC",
                "TACGGA",
                "ATGCAG",
                "CTTATA",
                "TCCATC"
        };
        assertFalse(validation.isMutant(dna));
    }

    @Test(expected = UnsupportedLetter.class)
    public void testUnsupportedLetter() throws UnsupportedLetter {
        final String [] dna = {
                "ATGCGA",
                "CAGGGC",
                "TACGGA",
                "ATGXAG",
                "CGTATA",
                "TCATTC"
        };
        validation.isMutant(dna);
    }

    @Test(expected = UnsupportedDimensionArray.class)
    public void testNotValidArrayDimension() {
        final String [] dna = {
                "ATGCGAA",
                "CTGAGCA",
                "TACGGAA",
                "ATGCAGA",
                "CGTATAA",
                "TCATTC"
        };
        validation.isMutant(dna);
    }

}
