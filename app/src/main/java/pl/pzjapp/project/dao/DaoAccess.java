package pl.pzjapp.project.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import pl.pzjapp.project.model.City;

@Dao
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public void insertCity(City city); //it can also return a Long which would be a ROWID.
    //TODO update inserts remove comments if all inforamtion will get validated.

    @Update(onConflict = OnConflictStrategy.FAIL)
    public void updateCity(City city);

    @Delete
    public void deleteCityById(City... cities);

    @Query("SELECT * FROM city")
    public City[] loadAllCitiesWithFullInformation();
}
