package pl.pzjapp.project.tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import java.util.List;

import pl.pzjapp.project.ItemClickListener;
import pl.pzjapp.project.R;
import pl.pzjapp.project.model.WeatherDataModel;
import pl.pzjapp.project.utils.UtilsForApp;

public class WeatherStatesAdapter extends RecyclerView.Adapter<WeatherStatesHolder> {
    private Context context;
    private List<WeatherDataModel> list;
    private static final String ICONURLDIRECTORY = UtilsForApp.fromTestConfigFile().get("api.iconurl");

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
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherStatesHolder weatherStatesHolder, int i) {
        WeatherDataModel currentWeatherState = list.get(i);
        float temperature = currentWeatherState.getTemp();
        float windSpeed = currentWeatherState.getWindSpeed();
        float pressure = currentWeatherState.getPressure();
        String icon = currentWeatherState.getIconRef();
        weatherStatesHolder.tvCityName.setText(currentWeatherState.getCityData().getCityName());
        weatherStatesHolder.tvDescription.setText(currentWeatherState.getWeatherState());
        weatherStatesHolder.tvTemperature.setText(context.getResources().getString(R.string.item_temperature) + temperature);
        weatherStatesHolder.tvWindSpeed.setText(context.getResources().getString(R.string.item_wind_speed) + windSpeed);
        weatherStatesHolder.tvPressure.setText(context.getResources().getString(R.string.item_pressure) + pressure);
        weatherStatesHolder.tvDate.setText(currentWeatherState.getDate());
        loadIcon(icon, weatherStatesHolder.ivIcon);
        weatherStatesHolder.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, boolean longClick) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void loadIcon(String iconRef, ImageView currentImageView)
    {
        Picasso.get().load(ICONURLDIRECTORY + iconRef + ".png").
                resize(150, 150).into(currentImageView);
    }
}
