package pl.pzjapp.project;

import android.arch.persistence.room.Database;

import pl.pzjapp.project.dao.DaoAccess;
import pl.pzjapp.project.model.City;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class TheAppDatabase {

    public abstract DaoAccess daoAccess();
}
