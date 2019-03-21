package pl.pzjapp.project.persistence.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

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
        theAppDatabase = Room.databaseBuilder(context, theAppDatabase.getClass(), DB_NAME).build();
    }

//
//    public void insertCity(String cityName, String country, int id) {
//
//        City dbCity = new City.CityBuilder()
//                .setCityName(cityName)
//                .setCityId(id)
//                .setCountry(country)
//                .build();
//
//        insertCity(dbCity);
//    }

    @SuppressLint("StaticFieldLeak")
    public void insertCity(final City city) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                theAppDatabase.daoAccess().insertCity(city);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getCityById(final int index) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                theAppDatabase.daoAccess().getCityById(index);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getAllCities() {
        new AsyncTask<Void, Void, LiveData<List<City>>>() {
            @Override
            protected LiveData<List<City>> doInBackground(Void... voids) {
                return theAppDatabase.daoAccess().getAllCities();
            }
        }.execute();
    }
}
