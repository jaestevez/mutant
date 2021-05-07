package com.example.mutant.app.mapper.impl;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.dao.MutantDAO;
import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.mapper.MapperMatrix;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
public class MapperMatrixTest {

    @Autowired
    private MapperMatrix mapperMatrix;
    @MockBean
    private MutantDAO mutantDAO;
    @MockBean
    private StatsDAO statsDAO;

    @Test
    public void testMapperToMatrix(){

        final String [] dna = {
                "ATGCGA",
                "CTGAGC",
                "TACGGA",
                "ATGCAG",
                "CGTATA",
                "TCATTC"
        };

        final String [][] matrixOut = {
                {"A","T","G","C","G","A"},
                {"C","T","G","A","G","C"},
                {"T","A","C","G","G","A"},
                {"A","T","G","C","A","G"},
                {"C","G","T","A","T","A"},
                {"T","C","A","T","T","C"}
        };
        String [][] matrix = mapperMatrix.dnaToMatrix(dna);
        assertArrayEquals(matrix, matrixOut);
    }
}
