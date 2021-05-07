package com.example.mutant.app.controller;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.dto.StatsDTO;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import com.example.mutant.app.service.MutantService;
import com.example.mutant.app.service.StatsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
@WebMvcTest(StatisticsController.class)
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    @Test
    public void shouldReturnOk() throws Exception {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setRatio(0.50f);
        statsDTO.setCountHumanDna(1);
        statsDTO.setCountMutantDna(1);
        when(statsService.getStats()).thenReturn(statsDTO);
        this.mockMvc.perform(get("/stats").contentType(APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"ratio\":0.5,\"count_mutant_dna\":1,\"count_human_dna\":1}")));
    }

}
