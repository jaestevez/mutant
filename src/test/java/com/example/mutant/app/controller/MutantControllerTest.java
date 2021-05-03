package com.example.mutant.app.controller;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import com.example.mutant.app.service.MutantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
@WebMvcTest(MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @Test
    public void shouldReturnOk() throws Exception {
        when(mutantService.validateMutant(any(MutantDTO.class))).thenReturn(true);
        final String requestJson = "{\"dna\":[\"ATGCGA\",\"CTGAGC\",\"TACGGA\",\"ATGCAG\",\"CGTATA\",\"TCATTC\"]}";
        this.mockMvc.perform(post("/mutant").contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(emptyOrNullString()));
    }

    @Test
    public void shouldReturnNoOk() throws Exception {
        when(mutantService.validateMutant(any(MutantDTO.class))).thenReturn(false);
        final String requestJson = "{\"dna\":[\"ATGCGA\",\"CTGAGC\",\"TACGGA\",\"ATGCAG\",\"CGTATA\",\"TCATTC\"]}";
        this.mockMvc.perform(post("/mutant").contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)).andDo(print()).andExpect(status().isForbidden())
                .andExpect(content().string(emptyOrNullString()));
    }

    @Test
    public void shouldReturnExceptionUnsupportedLetter() throws Exception {
        when(mutantService.validateMutant(any(MutantDTO.class))).thenThrow(UnsupportedLetter.class);
        final String requestJson = "{\"dna\":[\"ATGCGA\",\"CTGAGC\",\"TACGGA\",\"ATGCAG\",\"CGTATA\",\"TCATTC\"]}";
        this.mockMvc.perform(post("/mutant").contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(emptyOrNullString()));
    }

    @Test
    public void shouldReturnExceptionUnsupportedDimensionArray() throws Exception {
        when(mutantService.validateMutant(any(MutantDTO.class))).thenThrow(UnsupportedDimensionArray.class);
        final String requestJson = "{\"dna\":[\"ATGCGA\",\"CTGAGC\",\"TACGGA\",\"ATGCAG\",\"CGTATA\",\"TCATTC\"]}";
        this.mockMvc.perform(post("/mutant").contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(emptyOrNullString()));
    }
}
