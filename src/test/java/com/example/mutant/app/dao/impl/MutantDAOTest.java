package com.example.mutant.app.dao.impl;

import com.example.mutant.app.SpringbootApplication;
import com.example.mutant.app.dao.DataStoreDAO;
import com.example.mutant.app.dao.MutantDAO;
import com.example.mutant.app.model.Mutant;
import com.example.mutant.app.utils.ValidationType;
import com.google.cloud.Timestamp;
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

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootApplication.class })
public class MutantDAOTest {
    private LocalDatastoreHelper localDatastoreHelper = LocalDatastoreHelper.create();
    private Datastore datastore;
    @MockBean
    private DataStoreFactory dataStoreFactory;

    @Autowired
    private MutantDAO mutantDAO;

    @Before
    public void setUp() throws IOException, InterruptedException {
        localDatastoreHelper.start();
        datastore = localDatastoreHelper.getOptions().getService();
    }

    @Test
    public void testCreateRecord() {
        DataStoreDAO.setDatastore(datastore);
        Mutant mutant = new Mutant();
        mutant.setDna("dna");
        mutant.setResult(true);
        mutant.setCreated(Timestamp.now());
        assertNotNull(mutantDAO.saveMutant(mutant));
    }

}
