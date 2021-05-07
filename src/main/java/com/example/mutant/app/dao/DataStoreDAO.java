package com.example.mutant.app.dao;

import com.example.mutant.app.dao.impl.DataStoreFactory;
import com.google.cloud.datastore.Datastore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class DataStoreDAO {
    protected static Datastore datastore;
    @Autowired
    private DataStoreFactory dataStoreFactory;

    @PostConstruct
    private void init(){
        if(datastore == null){
            datastore = dataStoreFactory.getDataStore();
        }
    }

    public static void setDatastore(Datastore datastore) {
        DataStoreDAO.datastore = datastore;
    }
}
