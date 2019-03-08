package pl.pzjapp.project.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import pl.pzjapp.project.WeatherDataModel;
import pl.pzjapp.project.model.CityDataModel;

public class UtilsForApp {
    private static final String FILE_PATH = "config.properties";
    Properties properties;

    public UtilsForApp(Properties properties) {
        this.properties = properties;
    }

    public UtilsForApp() {

    }

    /**
     * @param propertyName
     * @return
     */
    public String get(String propertyName) {
        return properties.getProperty(propertyName);
    }

    /**
     * @param propertyName
     * @param defa
     * @return
     */
    public String getDefault(String propertyName, String defa) {
        return properties.getProperty(propertyName, defa);
    }

    /**
     * Fabricate method for getting standard test config.
     *
     * @return
     */
    public static UtilsForApp fromTestConfigFile() {
        return new UtilsForApp(readProperties(FILE_PATH));
    }

    public static Properties readProperties(String filePath) {
        Log.e(filePath, "error");
        Properties propertiesFromFile = new Properties();
        try {
            ClassLoader classLoaderForUtils = UtilsForApp.class.getClassLoader();
            if (classLoaderForUtils != null)
                propertiesFromFile.load(classLoaderForUtils.getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read test configuration file. Error: " + e.getMessage(), e);
        }
        Log.i("PROPERTIES:", propertiesFromFile.toString());
        return propertiesFromFile;
    }

    public ArrayList<WeatherDataModel> parseJSON(StringBuilder code, int limit, int cityId) {
        ArrayList<WeatherDataModel> data = new ArrayList<>();
        try {
            JSONObject mainJsonObject = new JSONObject(code.toString());
            CityDataModel cityData = new CityDataModel();
            cityData.setCityId(cityId);
            cityData.setCityName(mainJsonObject.getJSONObject("city").getString("name"));
            cityData.setCountry(mainJsonObject.getJSONObject("city").getString("country"));
            JSONArray jsonArray = mainJsonObject.getJSONArray("list");
            int n = jsonArray.length();

            if (limit != 0) {
                for (int i = n - limit; i < n; i++) {
                    JSONObject mainObject = (JSONObject) jsonArray.getJSONObject(i).get("main");
                    WeatherDataModel temporaryModel = setDataModeInfo(cityData, jsonArray, i, mainObject);
                    data.add(temporaryModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private WeatherDataModel setDataModeInfo(CityDataModel cityData, JSONArray jsonArray, int i, JSONObject mainObject) throws JSONException {
        JSONArray weatherArray = jsonArray.getJSONObject(i).getJSONArray("weather");
        JSONObject windObject = (JSONObject) jsonArray.getJSONObject(i).get("wind");
        WeatherDataModel temporaryModel = new WeatherDataModel();
        temporaryModel.setCityData(cityData);
        temporaryModel.setWeatherState(weatherArray.getJSONObject(0).getString("description"));
        temporaryModel.setTemp((float) mainObject.getDouble("temp"));
        temporaryModel.setTempMin((float) mainObject.getDouble("temp_min"));
        temporaryModel.setTempMax((float) mainObject.getDouble("temp_max"));
        temporaryModel.setHumidity((float) mainObject.getDouble("humidity"));
        temporaryModel.setPressure((float) mainObject.getDouble("pressure"));
        temporaryModel.setWindSpeed((float) windObject.getDouble("speed"));
        temporaryModel.setDate((String) jsonArray.getJSONObject(i).get("dt_txt"));
        temporaryModel.setIconRef(weatherArray.getJSONObject(0).getString("icon"));
        return temporaryModel;
    }
}
