package pl.pzjapp.project.persistence.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "city")
public class City implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "city_name")
    private String cityName;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "icon_ref")
    private String iconRef;
    @ColumnInfo(name = "city_id")
    private int cityId;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    public City(String cityName, String country, String date, String iconRef, int cityId, float windSpeed, float pressure, float humidity, float temperature) {
        this.cityName = cityName;
        this.country = country;
        this.date = date;
        this.iconRef = iconRef;
        this.cityId = cityId;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public City() {

    }

    public static class CityBuilder {
        private String cityName;
        private String country;
        private String date;
        private String iconRef;
        private int cityId;
        private float windSpeed;
        private float pressure;
        private float humidity;
        private float temperature;

        public CityBuilder() {
        }

        public CityBuilder setCityName(String city) {
            this.cityName = city;
            return this;
        }

        public CityBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public CityBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public CityBuilder setIconRef(String iconRef) {
            this.iconRef = iconRef;
            return this;
        }

        public CityBuilder setCityId(int cityId) {
            this.cityId = cityId;
            return this;
        }

        public CityBuilder setWindSpeed(float windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public CityBuilder setPressure(float pressure) {
            this.pressure = pressure;
            return this;
        }

        public CityBuilder setHumidity(float humidity) {
            this.humidity = humidity;
            return this;
        }

        public CityBuilder setTemperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public String getCityName() {
            return cityName;
        }

        public String getCountry() {
            return country;
        }

        public String getDate() {
            return date;
        }

        public String getIconRef() {
            return iconRef;
        }

        public float getWindSpeed() {
            return windSpeed;
        }

        public float getPressure() {
            return pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public float getTemperature() {
            return temperature;
        }

        public City build() {
            return new City(cityName, country, date, iconRef, cityId, windSpeed, pressure, humidity, temperature);
        }
    }

}
