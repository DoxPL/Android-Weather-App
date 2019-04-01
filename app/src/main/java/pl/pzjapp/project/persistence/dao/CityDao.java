/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pl.pzjapp.project.persistence.model.City;

@Dao
public interface CityDao {
    @Insert
    void insert(City object);

    @Update
    void update(City object);

    @Delete
    void delete(City city);

    @Query("SELECT * FROM city")
    List<City> getAllCities();

    @Query("SELECT * FROM city WHERE city_id=:cityId")
    City getCityById(int cityId);
}
