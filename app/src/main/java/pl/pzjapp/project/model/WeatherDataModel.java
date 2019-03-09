package pl.pzjapp.project.model;

import pl.pzjapp.project.model.CityDataModel;

public class WeatherDataModel {
    CityDataModel cityData;
    private String date;
    private String weatherState;
    private String iconRef;
    private float windSpeed;
    private float pressure;
    private float humidity;
    private float temp;
    private float tempMin;
    private float tempMax;

    public CityDataModel getCityData() {
        return cityData;
    }

    public void setCityData(CityDataModel cityData) {
        this.cityData = cityData;
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

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public String getWeatherState() {
        return weatherState;
    }

    public void setWeatherState(String weatherState) {
        this.weatherState = weatherState;
    }

    @Override
    public String toString() {
        return "WeatherDataModel{" +
                "cityData=" + cityData +
                ", date='" + date + '\'' +
                ", weatherState='" + weatherState + '\'' +
                ", iconRef='" + iconRef + '\'' +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", temp=" + temp +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}
