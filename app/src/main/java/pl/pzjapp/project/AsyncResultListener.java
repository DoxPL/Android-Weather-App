package pl.pzjapp.project;

import java.util.ArrayList;

import pl.pzjapp.project.model.WeatherDataModel;

public interface AsyncResultListener {
    void fillView(ArrayList<WeatherDataModel> data);
}
