package com.example.mutant.app.dao;

import com.example.mutant.app.model.Mutant;
import com.google.cloud.datastore.Entity;

public interface MutantDAO{
    Entity saveMutant(Mutant mutant);
}
