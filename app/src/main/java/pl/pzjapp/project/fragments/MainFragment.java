package pl.pzjapp.project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import pl.pzjapp.project.AsyncResultListener;
import pl.pzjapp.project.DataDownloader;
import pl.pzjapp.project.model.WeatherDataModel;
import pl.pzjapp.project.R;
import pl.pzjapp.project.tools.WeatherStatusAdapter;

public class MainFragment extends Fragment implements AsyncResultListener {
    private RecyclerView rvWeatherStates;
    private ProgressBar progressBar;
    private Spinner spCities;
    private WeatherStatusAdapter adapter;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayList<WeatherDataModel> listItems;
    private ArrayList<String> spinnerItems;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        spCities = view.findViewById(R.id.spCities);
        rvWeatherStates = view.findViewById(R.id.rvWeatherStates);
        progressBar = view.findViewById(R.id.progressBar);
        listItems = new ArrayList<WeatherDataModel>();
        spinnerItems = new ArrayList<String>();
        spinnerItems.add("Example city");
        spinnerItems.add("Test");
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCities.setAdapter(spinnerAdapter);
        adapter = new WeatherStatusAdapter(getContext(), listItems);
        rvWeatherStates.setHasFixedSize(true);
        rvWeatherStates.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWeatherStates.setAdapter(adapter);
        DataDownloader downloaderTask = new DataDownloader(1851632, 5,
                MainFragment.this);
        downloaderTask.execute();
        return view;
    }

    @Override
    public void fillView(ArrayList<WeatherDataModel> data) {
        for(WeatherDataModel item : data)
        {
            this.listItems.add(item);
            adapter.notifyDataSetChanged();
        }
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem swDisplaySpinner = menu.findItem(R.id.swShowSpinner);
        swDisplaySpinner.setVisible(true);
        swDisplaySpinner.setChecked(false);
        Switch actionBarSwitch = menu.findItem(R.id.swShowSpinner).
                getActionView().findViewById(R.id.swToolbar);
        actionBarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    spCities.setVisibility(View.VISIBLE);
                }
                else
                {
                    spCities.setVisibility(View.GONE);
                }
            }
        });
    }
}
