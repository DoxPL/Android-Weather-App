package pl.pzjapp.project.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import pl.pzjapp.project.DataModel;

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

    public ArrayList<DataModel> parseJSON(StringBuilder code, int limit) {
        ArrayList<DataModel> data = new ArrayList<>();
        try {
            JSONObject mainJsonObject = new JSONObject(code.toString());
            String city = mainJsonObject.getJSONObject("city").getString("name");
            String country = mainJsonObject.getJSONObject("city").getString("country");
            JSONArray jsonArray = mainJsonObject.getJSONArray("list");
            int n = jsonArray.length();

            if (limit != 0) {
                for (int i = n - limit; i < n; i++) {
                    JSONObject mainObject = (JSONObject) jsonArray.getJSONObject(i).get("main");
                    JSONObject windObject = (JSONObject) jsonArray.getJSONObject(i).get("wind");
                    JSONArray weatherArray = jsonArray.getJSONObject(i).getJSONArray("weather");
                    DataModel temporaryModel = setDataModeInfo(city, country, jsonArray, i, mainObject);
                    data.add(temporaryModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private DataModel setDataModeInfo(String city, String country, JSONArray jsonArray, int i, JSONObject mainObject) throws JSONException {
        DataModel temporaryModel = new DataModel();
        temporaryModel.setCity(city);
        temporaryModel.setCityId(mainObject.getInt("id"));
        temporaryModel.setCountry(country);
        temporaryModel.setWeatherState((jsonArray.getJSONObject(0).getString("description")));
        temporaryModel.setTemp((float) mainObject.getDouble("temp"));
        temporaryModel.setTempMin((float) mainObject.getDouble("temp_min"));
        temporaryModel.setTempMax((float) mainObject.getDouble("temp_max"));
        temporaryModel.setHumidity((float) mainObject.getDouble("humidity"));
        temporaryModel.setPressure((float) mainObject.getDouble("pressure"));
        temporaryModel.setWindSpeed((float) mainObject.getDouble("speed"));
        temporaryModel.setDate((String) jsonArray.getJSONObject(i).get("dt_txt"));
        temporaryModel.setIconRef(jsonArray.getJSONObject(0).getString("icon"));
        return temporaryModel;
    }
}
