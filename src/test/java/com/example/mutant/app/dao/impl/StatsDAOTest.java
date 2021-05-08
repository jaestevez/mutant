package com.example.mutant.app.dao.impl;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.dao.DataStoreDAO;
import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.utils.ValidationType;
import com.google.cloud.datastore.*;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
public class StatsDAOTest {
    private LocalDatastoreHelper localDatastoreHelper = LocalDatastoreHelper.create();
    private Datastore datastore;
    @MockBean
    private DataStoreFactory dataStoreFactory;

    @Autowired
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
    public void testGetStatsEmpty() {
        DataStoreDAO.setDatastore(datastore);
        assertNull(statsDAO.getStats());
    }

    @Test
    public void testGetStatsOk(){
        dataSet();
        DataStoreDAO.setDatastore(datastore);
        Entity result = statsDAO.getStats();
        assertNotNull(result);
        assertEquals(2, result.getProperties().size());
    }
    private void dataSet(){
        Key taskKey = datastore.newKeyFactory().setKind("Stats").newKey("stats_check");
        Entity stat = Entity.newBuilder(taskKey)
                .set(ValidationType.DS_TYPE_MUTANT.type, 100L)
                .set(ValidationType.DS_TYPE_HUMAN.type, 300L)
                .build();
        datastore.put(stat);
    }

    @Test
    public void testCreateStatsWithData(){
        dataSet();
        DataStoreDAO.setDatastore(datastore);
        Entity result = statsDAO.insertOrUpdateStats(ValidationType.DS_TYPE_MUTANT.type);
        assertNotNull(result);
    }

    @Test
    public void testCreateStatsWithoutData(){
        DataStoreDAO.setDatastore(datastore);
        Entity result = statsDAO.insertOrUpdateStats(ValidationType.DS_TYPE_MUTANT.type);
        assertNotNull(result);
    }
}
