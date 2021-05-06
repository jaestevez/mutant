package com.example.mutant.app.dao.impl;

import com.example.mutant.app.dao.DataStoreInstance;
import com.example.mutant.app.dao.StatsDAO;
import com.example.mutant.app.utils.Constants;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import org.springframework.stereotype.Component;

@Component
public class StatsDAOImpl extends DataStoreInstance implements StatsDAO{
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
     */
    public void insertOrUpdateStats(String kind) {
        Entity entity = getStats();
        if (entity == null) {
            //insert
            Key taskKey = datastore.newKeyFactory().setKind("Stats").newKey("stats_check");
            Entity stat = Entity.newBuilder(taskKey)
                    .set(Constants.DS_TYPE_MUTANT, kind.equals(Constants.DS_TYPE_MUTANT) ? 1L : 0L)
                    .set(Constants.DS_TYPE_HUMAN, kind.equals(Constants.DS_TYPE_HUMAN) ? 1L : 0L)
                    .build();
            datastore.put(stat);
        } else {
            //update
            Entity task = Entity.newBuilder(entity).set(kind, entity.getLong(kind) + 1).build();
            datastore.update(task);
        }
    }
}
