package pl.pzjapp.project.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "city")
public class City {

    @PrimaryKey
    private final int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "country")
    public String country = "country";
    @ColumnInfo(name = "lon")
    public String longtitude = "lon";
    @ColumnInfo(name = "lat")
    public String latitude = "lat";

    public City(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
