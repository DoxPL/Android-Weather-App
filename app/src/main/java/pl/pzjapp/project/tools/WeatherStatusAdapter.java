package pl.pzjapp.project.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import pl.pzjapp.project.utils.AppUtils;

public class WeatherStatusAdapter extends RecyclerView.Adapter<WeatherStatesHolder> {
    private Context context;
    private List<WeatherDataModel> list;
    private static final String ICON_URL_DIRECTORY = AppUtils.fromTestConfigFile().get("api.iconurl");

    /**
     * WeatherStatusAdapter constructor
     *
     * @param context context of class, where adapter is created
     * @param list    ArrayList of data objects that will be used to fill each list element
     */
    public WeatherStatusAdapter(Context context, List<WeatherDataModel> list) {
        this.context = context;
        this.list = list;
    }


    /**
     * @param viewGroup The ViewGroup into which the new View will be added after it is bound to an adapter position (Android docs)
     * @param i         The view type of the new View (Android docs)
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
        initComponents(weatherStatesHolder, list.get(i));
        weatherStatesHolder.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, final int position, boolean longClick) {
                if (longClick) {
                    createDialog(position);
                }
            }
        });
    }

    /**
     * Assigns values to components that will be displayed in specified view
     * @param weatherStatesHolder Holder of current list item
     * @param currentWeatherState Object with weather data
     */
    private void initComponents(WeatherStatesHolder weatherStatesHolder, WeatherDataModel currentWeatherState) {
        float temperature = currentWeatherState.getTemp();
        float windSpeed = currentWeatherState.getWindSpeed();
        float pressure = currentWeatherState.getPressure();
        String iconRef = currentWeatherState.getIconRef();
        weatherStatesHolder.tvCityName.setText(currentWeatherState.getCityData().getCityName());
        weatherStatesHolder.tvDate.setText(currentWeatherState.getDate());
        weatherStatesHolder.tvDescription.setText(context.getString(R.string.state) +
                currentWeatherState.getWeatherState());
        weatherStatesHolder.tvTemperature.setText(context.getResources().getString(R.string.item_temperature) + temperature +
                context.getString(R.string.unit_celsius_degree));
        weatherStatesHolder.tvWindSpeed.setText(context.getResources().getString(R.string.item_wind_speed) + windSpeed +
                context.getString(R.string.unit_kmph));
        weatherStatesHolder.tvPressure.setText(context.getResources().getString(R.string.item_pressure) + pressure +
                context.getString(R.string.unit_hpa));
        loadIcon(iconRef, weatherStatesHolder.ivIcon);
    }

    /**
     * Creates and displays a dialog after item long click, that asks user if record may be removed
     * @param itemPosition Position of current list element
     */
    private void createDialog(final int itemPosition) {
        DialogInterface.OnClickListener dialogClickCallback = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        list.remove(itemPosition);
                        notifyDataSetChanged();
                        //Todo: Remove record from database
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setMessage(context.getResources().getString(R.string.dialog_remove_text))
                .setPositiveButton(context.getResources().getString(R.string.dialog_btn_yes), dialogClickCallback)
                .setNegativeButton(context.getResources().getString(R.string.dialog_btn_cancel), dialogClickCallback).show();
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
     *
     * @param iconRef          Picture name obtained from JSON document
     * @param currentImageView Reference to ImageView of item layout
     */
    private void loadIcon(String iconRef, ImageView currentImageView) {
        Picasso.get().load(ICON_URL_DIRECTORY + iconRef + ".png").
                resize(150, 150).into(currentImageView);
    }
}
