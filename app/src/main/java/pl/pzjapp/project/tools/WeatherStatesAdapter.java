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

    /**
    * WeatherStatesAdapter constructor
    * @param context context of class, where adapter is created
    * @param list ArrayList of data objects that will be used to fill each list element
    */
    public WeatherStatesAdapter(Context context, List<WeatherDataModel> list)
    {
        this.context = context;
        this.list = list;
    }


    /**
    * @return WeatherStatesHolder from View
    */
    @NonNull
    @Override
    public WeatherStatesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.weather_state_item, null);
        WeatherStatesHolder holder = new WeatherStatesHolder(view);
        return holder;
    }

    /**
    * Called by RecyclerView to update view at specified position
    */
    @Override
    public void onBindViewHolder(@NonNull WeatherStatesHolder weatherStatesHolder, int i) {
        WeatherDataModel currentWeatherState = list.get(i);
        float temperature = currentWeatherState.getTemp();
        float windSpeed = currentWeatherState.getWindSpeed();
        float pressure = currentWeatherState.getPressure();
        String icon = currentWeatherState.getIconRef();
        weatherStatesHolder.tvCityName.setText(currentWeatherState.getCityData().getCityName());
        weatherStatesHolder.tvDescription.setText(context.getString(R.string.state) +
                currentWeatherState.getWeatherState());
        weatherStatesHolder.tvTemperature.setText(context.getResources().getString(R.string.item_temperature) + temperature +
                context.getString(R.string.unit_celsius_degree));
        weatherStatesHolder.tvWindSpeed.setText(context.getResources().getString(R.string.item_wind_speed) + windSpeed +
                context.getString(R.string.unit_kmph));
        weatherStatesHolder.tvPressure.setText(context.getResources().getString(R.string.item_pressure) + pressure +
                context.getString(R.string.unit_hpa));
        weatherStatesHolder.tvDate.setText(currentWeatherState.getDate());
        loadIcon(icon, weatherStatesHolder.ivIcon);
        weatherStatesHolder.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, boolean longClick) {

            }
        });
    }

    /**
    * @return size of WeatherDataModel list
    */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
    * Loads a weather state icon from URL into ImageView
    * @param iconRef
    *   Picture name obtained from JSON document
    * @param currentImageView
    *   Reference to ImageView of item layout
    */
    private void loadIcon(String iconRef, ImageView currentImageView)
    {
        Picasso.get().load(ICONURLDIRECTORY + iconRef + ".png").
                resize(150, 150).into(currentImageView);
    }
}
