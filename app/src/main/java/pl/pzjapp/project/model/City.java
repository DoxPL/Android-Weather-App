package pl.pzjapp.project.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class City implements Serializable {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "icon_red")
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

    public City(String city, String country, String date, String iconRef, int cityId, float windSpeed, float pressure, float humidity, float temperature) {
        this.city = city;
        this.country = country;
        this.date = date;
        this.iconRef = iconRef;
        this.cityId = cityId;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public static class CityBuilder {
        private String city;
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

        public CityBuilder setCity(String city) {
            this.city = city;
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

        public String getCity() {
            return city;
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
            return new City(city, country, date, iconRef, cityId, windSpeed, pressure, humidity, temperature);
        }
    }

}
