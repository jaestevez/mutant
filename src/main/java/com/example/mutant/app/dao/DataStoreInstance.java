package com.example.mutant.app.dao;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

public abstract class DataStoreInstance {
    protected Datastore datastore;

    protected DataStoreInstance(){
        if(datastore == null){
            datastore = DatastoreOptions.getDefaultInstance().getService();
        }
    }
}
