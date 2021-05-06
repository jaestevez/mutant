package com.example.mutant.app.dao.impl;

import com.example.mutant.app.dao.DataStoreInstance;
import com.example.mutant.app.dao.MutantDAO;
import com.example.mutant.app.model.Mutant;
import com.example.mutant.app.utils.Constants;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import org.springframework.stereotype.Component;

@Component
public class MutantDAOImpl extends DataStoreInstance implements MutantDAO {
    /**
     * Almacena la validación realizada
     * @param mutant
     */
    public void saveMutant(Mutant mutant) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(mutant.isResult()? Constants.DS_TYPE_MUTANT:Constants.DS_TYPE_HUMAN);
        FullEntity<IncompleteKey> task = FullEntity.newBuilder(keyFactory.newKey())
                .set("dna", mutant.getDna())
                .set("result", mutant.isResult())
                .set("created", mutant.getCreated())
                .build();
        datastore.add(task);
    }
}
