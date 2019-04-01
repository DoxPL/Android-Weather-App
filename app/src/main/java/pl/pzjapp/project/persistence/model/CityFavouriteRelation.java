/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import java.util.Objects;

@Entity(tableName = "city_favourite_join",
        primaryKeys = {"cityId", "favouriteId"},
        foreignKeys = {
                @ForeignKey(entity = City.class,
                        parentColumns = "id",
                        childColumns = "cityId"
                ),
                @ForeignKey(entity = Favourite.class,
                        parentColumns = "id",
                        childColumns = "favouriteId")
        })
public class CityFavouriteRelation {
    public final int cityId;
    public final int favouriteId;

    public CityFavouriteRelation(int cityId, int favouriteId) {
        this.cityId = cityId;
        this.favouriteId = favouriteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityFavouriteRelation that = (CityFavouriteRelation) o;
        return cityId == that.cityId &&
                favouriteId == that.favouriteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, favouriteId);
    }
}
