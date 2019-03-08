package pl.pzjapp.project;

import java.util.ArrayList;

public interface AsyncResultListener {
    void fillView(ArrayList<WeatherDataModel> data);
}
