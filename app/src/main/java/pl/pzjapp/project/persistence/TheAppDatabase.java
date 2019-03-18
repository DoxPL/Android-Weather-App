package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pl.pzjapp.project.persistence.dao.DaoAccess;
import pl.pzjapp.project.persistence.model.City;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class TheAppDatabase extends RoomDatabase {

    private static TheAppDatabase INSTANCE;

    public abstract DaoAccess daoAccess();


    public static TheAppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), TheAppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
