/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.model.CityWeatherRelation;
import pl.pzjapp.project.persistence.model.Favourite;

@Dao
public interface CityWeatherDao {
    @Insert
    void insert(CityWeatherRelation cityWeatherRelation);

    @Query("SELECT * FROM city INNER JOIN city_weather_join ON " +
            "city.id=city_weather_join.cityId WHERE " +
            "city_weather_join.weatherId=:weatherId ")
    List<City> getCitiesForFavourite(final int weatherId);

    @Query("SELECT * FROM weather INNER JOIN city_weather_join ON " +
            "weather.id=city_weather_join.weatherId WHERE " +
            "city_weather_join.cityId=:cityId")
    List<Favourite> getFavouritesForCity(final int cityId);
}
