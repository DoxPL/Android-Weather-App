package pl.pzjapp.project.tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.pzjapp.project.ItemClickListener;
import pl.pzjapp.project.R;
import pl.pzjapp.project.model.WeatherDataModel;

public class WeatherStatesAdapter extends RecyclerView.Adapter<WeatherStatesHolder> {
    private Context context;
    private List<WeatherDataModel> list;

    public WeatherStatesAdapter(Context context, List<WeatherDataModel> list)
    {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public WeatherStatesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.weather_state_item, null);
        WeatherStatesHolder holder = new WeatherStatesHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherStatesHolder weatherStatesHolder, int i) {
        WeatherDataModel currentWeatherState = list.get(i);
        weatherStatesHolder.tvCityName.setText(currentWeatherState.getCityData().getCityName());
        weatherStatesHolder.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, boolean longClick) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
