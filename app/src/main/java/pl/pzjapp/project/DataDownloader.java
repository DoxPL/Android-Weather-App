package pl.pzjapp.project;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataDownloader extends AsyncTask<Void, Void, Void> {
    private int cityId;
    private int limit;
    private ArrayList<DataModel> data;
    private static final String APIKEY = "809d268c6dc02962f896e66e442616cd";
    public AsyncResultListener asyncResultListener = null;

    public DataDownloader(int cityId, int limit, AsyncResultListener asyncResultListener) {
        this.cityId = cityId;
        this.asyncResultListener = asyncResultListener;
        this.limit = limit;
    }

    protected Void doInBackground(Void... params) {
        data = new ArrayList<DataModel>();
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=" + this.cityId +
                    "&units=metric&appid=" + APIKEY);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while (line != null) {
                line = buffer.readLine();
                stringBuilder.append(line);
            }

            parseJSON(stringBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void parseJSON(StringBuilder code)
    {
        try {
            JSONObject mainJsonObject = new JSONObject(code.toString());
            String city = mainJsonObject.getJSONObject("city").getString("name");
            String country = mainJsonObject.getJSONObject("city").getString("country");
            JSONArray jsonArray = mainJsonObject.getJSONArray("list");
            int n = jsonArray.length();
            for (int i = (limit != 0) ? n - limit : 0; i < n; i++) {
                JSONObject mainObject = (JSONObject) jsonArray.getJSONObject(i).get("main");
                JSONObject windObject = (JSONObject) jsonArray.getJSONObject(i).get("wind");
                JSONArray weatherArray = jsonArray.getJSONObject(i).getJSONArray("weather");
                DataModel tmp = new DataModel();
                tmp.setCity(city);
                tmp.setCityId(this.cityId);
                tmp.setCountry(country);
                tmp.setWeatherState((weatherArray.getJSONObject(0).getString("description")));
                tmp.setTemp((float) mainObject.getDouble("temp"));
                tmp.setTempMin((float) mainObject.getDouble("temp_min"));
                tmp.setTempMax((float) mainObject.getDouble("temp_max"));
                tmp.setHumidity((float) mainObject.getDouble("humidity"));
                tmp.setPressure((float) mainObject.getDouble("pressure"));
                tmp.setWindSpeed((float) windObject.getDouble("speed"));
                tmp.setDate((String) jsonArray.getJSONObject(i).get("dt_txt"));
                tmp.setIconRef(weatherArray.getJSONObject(0).getString("icon"));
                data.add(tmp);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        asyncResultListener.fillView(data);
    }
}
