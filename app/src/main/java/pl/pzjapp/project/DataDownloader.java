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
    private ArrayList<DataModel> data;
    private static final String APIKEY = "809d268c6dc02962f896e66e442616cd";

    public DataDownloader(int cityId)
    {
        this.cityId = cityId;
        data = new ArrayList<DataModel>();
    }

    protected Void doInBackground(Void... params) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=" + this.cityId +
                    "&appid=" + APIKEY);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while (line != null) {
                line = buffer.readLine();
                stringBuilder.append(line);
            }


            JSONArray jsonArray = new JSONObject(stringBuilder.toString()).getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mainObject = (JSONObject) jsonArray.getJSONObject(i).get("main");
                JSONObject windObject = (JSONObject) jsonArray.getJSONObject(i).get("wind");
                JSONArray weatherArray = jsonArray.getJSONObject(i).getJSONArray("weather");
                DataModel tmp = new DataModel();
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
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        for(DataModel item : data)
        {
            MainFragment.tv.append("\nHumidity: " + item.getHumidity());
            MainFragment.tv.append("\nPressure: " + item.getPressure());
            MainFragment.tv.append("\nTemp: " + item.getTemp());
            MainFragment.tv.append("\nTempMin: " + item.getTempMin());
            MainFragment.tv.append("\nTempMax: " + item.getTempMax());
            MainFragment.tv.append("\nWind speed: " + item.getWindSpeed());
            MainFragment.tv.append("\nIcon: " + item.getIconRef());
            MainFragment.tv.append("\nDate: " + item.getDate() + "\n");
        }
    }

    public ArrayList<DataModel> getList()
    {
        return this.data;
    }
}
