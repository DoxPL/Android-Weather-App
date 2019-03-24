package pl.pzjapp.project.persistence.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;

import pl.pzjapp.project.persistence.DatabaseClient;
import pl.pzjapp.project.persistence.TheAppDatabase;
import pl.pzjapp.project.persistence.dao.DaoAccess;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.utils.AppUtils;

public class CityRepository {
    private static final AppUtils CONFIG = AppUtils.fromTestConfigFile();
    private static final String DB_NAME = CONFIG.get("db.name");
    private final DaoAccess DAO_ACCESS;
    private final Executor EXECUTOR;
    DatabaseClient databaseClient;

    public CityRepository(DaoAccess daoAccess, Executor executor) {
        this.DAO_ACCESS = daoAccess;
        this.EXECUTOR = executor;
    }

    @SuppressLint("StaticFieldLeak")
    public void insertCity(final City city) {
        final DaoAccess daoAccess = getDaoAccess();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                daoAccess.insertCity(city);
                return null;
            }
        }.execute();
    }


    @SuppressLint("StaticFieldLeak")
    public void getCityById(final String index) {
        final DaoAccess daoAccess = getDaoAccess();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                daoAccess.getCityById(index);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getAllCities() {
        final DaoAccess daoAccess = getDaoAccess();
        new AsyncTask<Void, Void, LiveData<List<City>>>() {
            @Override
            protected LiveData<List<City>> doInBackground(Void... voids) {
                return daoAccess.getAllCities();
            }
        }.execute();
    }

    private DaoAccess getDaoAccess() {
        TheAppDatabase theAppDatabase = databaseClient.getTheAppDatabase();
        return theAppDatabase.daoAccess();
    }

    public LiveData<City> getCity(final String id) {
        return getDaoAccess().getCityById(id);
    }
}

