/*
 * Developed by Adam Yunad
 */

package pl.pzjapp.project.persistence.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "weather")
public class Weather {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "icon_ref")
    private String iconRef;
    @ColumnInfo(name = "wind_speed")
    private float windSpeed;
    @ColumnInfo(name = "pressure")
    private float pressure;
    @ColumnInfo(name = "humidty")
    private float humidity;
    @ColumnInfo(name = "temperature")
    private float temperature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIconRef() {
        return iconRef;
    }

    public void setIconRef(String iconRef) {
        this.iconRef = iconRef;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return id == weather.id &&
                Float.compare(weather.windSpeed, windSpeed) == 0 &&
                Float.compare(weather.pressure, pressure) == 0 &&
                Float.compare(weather.humidity, humidity) == 0 &&
                Float.compare(weather.temperature, temperature) == 0 &&
                Objects.equals(date, weather.date) &&
                Objects.equals(iconRef, weather.iconRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, iconRef, windSpeed, pressure, humidity, temperature);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", iconRef='" + iconRef + '\'' +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }
}
