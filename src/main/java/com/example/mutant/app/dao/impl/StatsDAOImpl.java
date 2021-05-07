package com.example.mutant.app.dao.impl;

import com.example.mutant.app.dao.DataStoreDAO;
import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.utils.ValidationType;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import org.springframework.stereotype.Component;

@Component
public class StatsDAOImpl extends DataStoreDAO implements StatsDAO{
    /**
     * Consulta de estadisticas
     * @return
     */
    public Entity getStats(){
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Stats").build();
        QueryResults<Entity> tasks = datastore.run(query);
        Entity entity = null;
        if(tasks.hasNext()){
            entity = tasks.next();
        }
        return entity;
    }

    /**
     * Actualiza o crea las estadisticas
     * @param kind
     * @return
     */
    public Entity insertOrUpdateStats(String kind) {
        Entity entity = null;
        Entity entityIn = getStats();
        if (entityIn == null) {
            //insert
            Key taskKey = datastore.newKeyFactory().setKind("Stats").newKey("stats_check");
            Entity stat = Entity.newBuilder(taskKey)
                    .set(ValidationType.DS_TYPE_MUTANT.type, kind.equals(ValidationType.DS_TYPE_MUTANT.type) ? 1L : 0L)
                    .set(ValidationType.DS_TYPE_HUMAN.type, kind.equals(ValidationType.DS_TYPE_HUMAN.type) ? 1L : 0L)
                    .build();
            entity = datastore.add(stat);
        } else {
            //update
            entity = Entity.newBuilder(entityIn).set(kind, entityIn.getLong(kind) + 1).build();
            datastore.update(entity);
        }
        return entity;
    }
}
