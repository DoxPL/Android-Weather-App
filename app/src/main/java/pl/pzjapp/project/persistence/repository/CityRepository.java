/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import pl.pzjapp.project.persistence.TheAppDatabase;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.utils.AppUtils;

public class CityRepository {
    private static final AppUtils CONFIG = AppUtils.fromTestConfigFile();
    private static final String DB_NAME = CONFIG.get("db.name");

    private TheAppDatabase theAppDatabase;

    public CityRepository(@NonNull Context context) {
        theAppDatabase = Room.databaseBuilder(context, TheAppDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertCity(final City city) {
        theAppDatabase.getCityDao().insert(city);
    }

    @SuppressLint("StaticFieldLeak")
    public void getCityById(final int index) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                theAppDatabase.getCityDao().getCityById(index);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public List<City> getAllCities() {
        return theAppDatabase.getCityDao().getAllCities();
    }
}
