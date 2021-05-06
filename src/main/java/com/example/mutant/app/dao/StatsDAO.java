package com.example.mutant.app.dao;


import com.google.cloud.datastore.Entity;

public interface StatsDAO {
    Entity getStats();
    void insertOrUpdateStats(String kind);
}
