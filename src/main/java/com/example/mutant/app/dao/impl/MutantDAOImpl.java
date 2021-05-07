package com.example.mutant.app.dao.impl;

import com.example.mutant.app.dao.DataStoreDAO;
import com.example.mutant.app.dao.MutantDAO;
import com.example.mutant.app.model.Mutant;
import com.example.mutant.app.utils.ValidationType;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import org.springframework.stereotype.Component;

@Component
public class MutantDAOImpl extends DataStoreDAO implements MutantDAO {
    /**
     * Almacena la validación realizada
     * @param mutant
     * @return
     */
    public Entity saveMutant(Mutant mutant) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(mutant.isResult()? ValidationType.DS_TYPE_MUTANT.type: ValidationType.DS_TYPE_HUMAN.type);
        FullEntity<IncompleteKey> task = FullEntity.newBuilder(keyFactory.newKey())
                .set("dna", mutant.getDna())
                .set("result", mutant.isResult())
                .set("created", mutant.getCreated())
                .build();
        return datastore.add(task);
    }
}
