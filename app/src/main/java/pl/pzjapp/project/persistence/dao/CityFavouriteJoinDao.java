/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.model.CityFavouriteRelation;
import pl.pzjapp.project.persistence.model.Favourite;

@Dao
public interface CityFavouriteJoinDao {

    @Insert
    void insert(CityFavouriteRelation cityFavouriteRelation);

    @Query("SELECT * FROM city INNER JOIN city_favourite_join ON " +
            "city.id=city_favourite_join.cityId WHERE " +
            "city_favourite_join.favouriteId=:favouriteId ")
    List<City> getCitiesForFavourite(final int favouriteId);

    @Query("SELECT * FROM favourite INNER JOIN city_favourite_join ON " +
            "favourite.id=city_favourite_join.favouriteId WHERE " +
            "city_favourite_join.cityId=:cityId")
    List<Favourite> getFavouritesForCity(final int cityId);
}
