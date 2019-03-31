/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import pl.pzjapp.project.persistence.model.Favourite;

@Dao
public interface FavouriteDao {
    @Insert
    void insert(Favourite favourite);
    @Update
    void update(Favourite favourite);
    @Delete
    void delete(Favourite favourite);

}
