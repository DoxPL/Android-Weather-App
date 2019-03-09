package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.pzjapp.project.persistence.dao.DaoAccess;
import pl.pzjapp.project.persistence.model.City;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class TheAppDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
