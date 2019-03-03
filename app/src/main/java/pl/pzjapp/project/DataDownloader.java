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
    private int woeid;
    private ArrayList<DataModel> data;


    public DataDownloader(int woeid)
    {
        this.woeid = woeid;
        data = new ArrayList<DataModel>();
    }

    protected Void doInBackground(Void... params) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://www.metaweather.com/api/location/" + this.woeid);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while (line != null) {
                line = buffer.readLine();
                stringBuilder.append(line);
            }


            JSONArray jsonArray = new JSONObject(stringBuilder.toString()).getJSONArray("consolidated_weather");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DataModel tmp = new DataModel();
                tmp.setTemperature((int) jsonObject.getDouble("the_temp"));
                tmp.setHumidity((float) jsonObject.getDouble("humidity"));
                tmp.setPressure((float) jsonObject.getDouble("air_pressure"));
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
    }


    public ArrayList<DataModel> getList()
    {
        return data;
    }

}
