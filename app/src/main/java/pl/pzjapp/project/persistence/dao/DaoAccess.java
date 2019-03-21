package pl.pzjapp.project.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pl.pzjapp.project.persistence.model.City;

@Dao
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertCity(City city); //it can also return a Long which would be a ROWID.
    //TODO update inserts remove comments if all inforamtion will get validated.

    @Update(onConflict = OnConflictStrategy.FAIL)
    void updateCity(City city);

    @Delete
    void deleteCityById(City... cities);

    @Query("SELECT * FROM city")
    List<City> loadAllCitiesWithFullInformation();

    @Query("SELECT * FROM city")
    LiveData<List<City>> getAllCities();

    @Query("SELECT * FROM city WHERE id IN(:cityId)")
    City getCityById(int cityId);
}
