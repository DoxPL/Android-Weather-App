package pl.pzjapp.project;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import pl.pzjapp.project.model.WeatherDataModel;
import pl.pzjapp.project.utils.UtilsForApp;

public class DataDownloader extends AsyncTask<Void, Void, Void> {
    private static final UtilsForApp CONFIG = UtilsForApp.fromTestConfigFile();
    private static final String APIKEY = CONFIG.get("api.key");

    private int cityId;
    private int limit;
    private ArrayList<WeatherDataModel> data;
    private UtilsForApp utils = new UtilsForApp();
    public AsyncResultListener asyncResultListener;

    /**
    * Public constructor that accepts following parameters:
    * @param cityId
    *   ID of current city
    * @param limit
    *   Number of results to fetch from JSON document
    * @param asyncResultListener
    *   An interface with fillView method, that must be implemented in MainFragment
    */
    public DataDownloader(int cityId, int limit, AsyncResultListener asyncResultListener) {
        this.cityId = cityId;
        this.asyncResultListener = asyncResultListener;
        this.limit = limit;
    }

    /**
    * This async method opens an HttpURLConnection with OpenWeatherMap API, reads JSON document
    * and calls parseJSON function from utils to assign data to ArrayList
    * @return null
    */
    @Override
    protected Void doInBackground(Void... params) {
        data = new ArrayList<>();
        URL url;
        HttpURLConnection connection;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=" + this.cityId +
                    "&units=metric&appid=" + APIKEY);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
            }
            data = utils.parseJSON(stringBuilder, limit, cityId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * Called after doInBackground method is executed
    */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        asyncResultListener.fillView(data);
    }
}
