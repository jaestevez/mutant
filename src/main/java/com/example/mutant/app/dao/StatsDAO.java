package com.example.mutant.app.dao;


import com.google.cloud.datastore.Entity;

public interface StatsDAO {
    Entity getStats();
    Entity insertOrUpdateStats(String kind);
}
