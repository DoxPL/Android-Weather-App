/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.pzjapp.project.persistence.dao.CityDao;
import pl.pzjapp.project.persistence.dao.CityFavouriteJoinDao;
import pl.pzjapp.project.persistence.dao.CityWeatherDao;
import pl.pzjapp.project.persistence.dao.FavouriteDao;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.model.CityFavouriteRelation;
import pl.pzjapp.project.persistence.model.CityWeatherRelation;
import pl.pzjapp.project.persistence.model.Favourite;
import pl.pzjapp.project.persistence.model.Weather;

@Database(entities = {City.class, Favourite.class, Weather.class,CityFavouriteRelation.class, CityWeatherRelation.class}, version = 5, exportSchema = false)
public abstract class TheAppDatabase extends RoomDatabase {

    public abstract CityDao getCityDao();

    public abstract FavouriteDao getFavouriteDao();

    public abstract CityFavouriteJoinDao getCityFavouriteJoinDao();

    public abstract CityWeatherDao getCityWeatherDao();
}
