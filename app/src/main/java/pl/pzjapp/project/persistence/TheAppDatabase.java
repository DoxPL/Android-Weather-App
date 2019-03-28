/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.pzjapp.project.persistence.dao.CityDao;
import pl.pzjapp.project.persistence.dao.CityFavouriteJoinDao;
import pl.pzjapp.project.persistence.dao.FavouriteDao;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.model.CityFavouriteRelation;
import pl.pzjapp.project.persistence.model.Favourite;

@Database(entities = {City.class, Favourite.class, CityFavouriteRelation.class}, version = 4, exportSchema = false)
public abstract class TheAppDatabase extends RoomDatabase {

    public abstract CityDao getCityDao();

    public abstract FavouriteDao getFavouriteDao();

    public abstract CityFavouriteJoinDao getCityFavouriteJoinDao();
}
