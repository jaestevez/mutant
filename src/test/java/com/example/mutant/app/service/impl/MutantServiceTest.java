package com.example.mutant.app.service.impl;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.component.ValidationMutant;
import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.service.MutantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
public class MutantServiceTest {
    @Autowired
    private MutantService mutantService;

    @MockBean
    private ValidationMutant validation;

    @Test
    public void testValidateMutantNoOk(){
        MutantDTO mutant = new MutantDTO();
        mutant.setDna(null);
        when(validation.isMutant(null)).thenReturn(false);
        assertFalse(mutantService.validateMutant(mutant));
    }

    @Test
    public void testValidateMutantOk(){
        MutantDTO mutant = new MutantDTO();
        mutant.setDna(new String[]{"AAAA","CCCC", "TTTT", "CCCC"});
        when(validation.isMutant(new String[]{"AAAA","CCCC", "TTTT", "CCCC"})).thenReturn(true);
        assertTrue(mutantService.validateMutant(mutant));
    }
}
