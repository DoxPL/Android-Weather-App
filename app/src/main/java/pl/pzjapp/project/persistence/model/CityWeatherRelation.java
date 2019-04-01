/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import java.util.Objects;

@Entity(tableName = "city_weather_join",
        primaryKeys = {"cityId", "weatherId"},
        foreignKeys = {
                @ForeignKey(entity = City.class,
                        parentColumns = "id",
                        childColumns = "cityId"
                ),
                @ForeignKey(entity = Weather.class,
                        parentColumns = "id",
                        childColumns = "weatherId")
        })
public class CityWeatherRelation {

    public final int cityId;
    public final int weatherId;

    public CityWeatherRelation(int cityId, int weatherId) {
        this.cityId = cityId;
        this.weatherId = weatherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityWeatherRelation that = (CityWeatherRelation) o;
        return cityId == that.cityId &&
                weatherId == that.weatherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, weatherId);
    }
}
