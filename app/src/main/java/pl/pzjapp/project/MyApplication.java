package pl.pzjapp.project;

import android.app.Application;
import android.content.Context;

import java.util.List;

import pl.pzjapp.project.persistence.TheAppDatabase;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.repository.CityRepository;

public class MyApplication extends Application {
    private static final String DATABASE_NAME = "the-app";

    public void onCreate() {
        super.onCreate();
        Context applicationContext = getApplicationContext();
        CityRepository cityRepository = new CityRepository(applicationContext);
//        cityRepository.insertCity(populateDataBase());
        new Thread(new Runnable() {
            @Override
            public void run() {
                populateDataBase();
            }
        }).start();
        cityRepository.getAllCities();
        cityRepository.getCityById(0);


    }

    private City populateDataBase() {
        City city = new City();
        city.setCityName("dupa");
        city.setCountry("Poland");
        city.setDate("123");
        city.setWindSpeed(42);
        city.setIconRef("123");
        city.setCityId(123);
        city.setPressure(123);
        city.setHumidity(312);
        city.setTemperature(2);
        return city;
    }

    private List<City> getAllCities(TheAppDatabase theAppDatabase) {
        return theAppDatabase.getCityDao().getAllCities();
    }

}