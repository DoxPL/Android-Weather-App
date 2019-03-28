package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Room;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Objects;

import androidx.test.core.app.ApplicationProvider;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.repository.CityRepository;

@RunWith(RobolectricTestRunner.class)
public class TheAppDatabaseTest {


    private TheAppDatabase mDatabase;
    private CityRepository cityRepository;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.databaseBuilder(ApplicationProvider.getApplicationContext(), TheAppDatabase.class, "the-app").build();
        cityRepository = new CityRepository(ApplicationProvider.getApplicationContext());
        populateWithTestData();
        for (City c : cityRepository.getAllCities()) {
            System.out.println(c.toString());
        }
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void onFetchingNotes_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        System.out.println(cityRepository.getAllCities());
        cityRepository.getAllCities();
    }

    private City addCity(City city) {
        cityRepository.insertCity(city);
        return city;
    }

    private void populateWithTestData() {
        City city = new City();
        city.setCityName("Ajay");
        city.setCountry("Saini");
        city.setId(25);
        addCity(city);
    }


}