package pl.pzjapp.project.tools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pl.pzjapp.project.ItemClickListener;
import pl.pzjapp.project.R;

public class WeatherStatesHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ItemClickListener listener;
    protected TextView tvCityName;

    public WeatherStatesHolder(@NonNull View itemView) {
        super(itemView);
        this.tvCityName = itemView.findViewById(R.id.tvCityName);
    }

    public void setOnItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        listener.onItemClick(v, getAdapterPosition(), true);
        return false;
    }
}
