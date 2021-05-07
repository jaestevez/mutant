package com.example.mutant.app.dao.impl;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.stereotype.Component;

@Component
public class DataStoreFactory {
    public Datastore getDataStore(){
        return DatastoreOptions.getDefaultInstance().getService();
    }
}
