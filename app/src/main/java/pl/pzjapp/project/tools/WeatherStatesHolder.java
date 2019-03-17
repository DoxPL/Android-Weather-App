package pl.pzjapp.project.tools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.pzjapp.project.ItemClickListener;
import pl.pzjapp.project.R;

public class WeatherStatesHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ItemClickListener listener;
    protected TextView tvCityName;
    protected TextView tvDescription;
    protected TextView tvTemperature;
    protected TextView tvWindSpeed;
    protected TextView tvPressure;
    protected TextView tvDate;
    protected ImageView ivIcon;

    /**
    * Public constructor. Makes an intercations with layout's widgets to JAVA objects.
    * @param itemView current item view
    */
    public WeatherStatesHolder(@NonNull View itemView) {
        super(itemView);
        this.tvCityName = itemView.findViewById(R.id.tvCityName);
        this.tvDescription = itemView.findViewById(R.id.tvDescription);
        this.tvTemperature = itemView.findViewById(R.id.tvTemperature);
        this.tvWindSpeed = itemView.findViewById(R.id.tvWindSpeed);
        this.tvPressure = itemView.findViewById(R.id.tvPressure);
        this.tvDate = itemView.findViewById(R.id.tvDate);
        this.ivIcon = itemView.findViewById(R.id.ivWeatherIcon);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }
    /**
    * Assigns ItemClickListener
    * @param listener listener's interface to implement
    */
    public void setOnItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }

    /**
    * Method called after click of list element
    * @param v view of clicked element
    */
    @Override
    public void onClick(View v) {
        listener.onItemClick(v, getAdapterPosition(), false);
    }

    /**
    * Method called after long click of list element
    * @param v view of long-clicked element
    */
    @Override
    public boolean onLongClick(View v) {
        listener.onItemClick(v, getAdapterPosition(), true);
        return false;
    }
}
