package com.example.mutant.app.service.impl;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.dao.MutantDAO;
import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.dto.StatsDTO;
import com.example.mutant.app.service.StatsService;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.testing.LocalDatastoreHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.threeten.bp.Duration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
public class StatsServiceTest {
    private LocalDatastoreHelper localDatastoreHelper = LocalDatastoreHelper.create();
    private Datastore datastore;
    @Autowired
    private StatsService statsService;

    @MockBean
    private MutantDAO mutantDAO;
    @MockBean
    private StatsDAO statsDAO;

    @Before
    public void setUp() throws IOException, InterruptedException {
        localDatastoreHelper.start();
        datastore = localDatastoreHelper.getOptions().getService();
    }

    @After
    public void tearDown() {
        try {
            localDatastoreHelper.stop(Duration.ofSeconds(5000));
        } catch (IOException | InterruptedException | TimeoutException e) {

        }
    }

    @Test
    public void testServiceMutantOk(){
        Key taskKey = datastore.newKeyFactory()
                .setKind("Stats")
                .newKey("sampleTask");
        Entity entity = Entity.newBuilder(taskKey)
                .set("mutant", 20L)
                .set("no-mutant", 10L)
                .build();
        when(statsDAO.getStats()).thenReturn(entity);
        StatsDTO result = statsService.getStats();
        assertNotNull(result);
        assertEquals(10, result.getCountHumanDna());
        assertEquals(20, result.getCountMutantDna());
        assertEquals(0.66666, result.getRatio(),0.0001);
    }

    @Test
    public void testServiceMutantEmpty(){
        when(statsDAO.getStats()).thenReturn(null);
        StatsDTO result = statsService.getStats();
        assertNotNull(result);
        assertEquals(0, result.getCountHumanDna());
        assertEquals(0, result.getCountMutantDna());
        assertEquals(0.0f, result.getRatio(), 0.0);
    }

}
