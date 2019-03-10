package pl.pzjapp.project.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.pzjapp.project.AsyncResultListener;
import pl.pzjapp.project.DataDownloader;
import pl.pzjapp.project.model.CityDataModel;
import pl.pzjapp.project.model.WeatherDataModel;
import pl.pzjapp.project.R;
import pl.pzjapp.project.tools.WeatherStatesAdapter;

public class MainFragment extends Fragment implements AsyncResultListener {
    private RecyclerView rvWeatherStates;
    private ProgressBar progressBar;
    private WeatherStatesAdapter adapter;
    private ArrayList<WeatherDataModel> listItems;
    private OnFragmentInteractionListener mListener;

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
        //TODO Create RecyclerView with items that represents weather data
        rvWeatherStates = view.findViewById(R.id.rvWeatherStates);
        progressBar = view.findViewById(R.id.progressBar);
        listItems = new ArrayList<WeatherDataModel>();
        adapter = new WeatherStatesAdapter(getContext(), listItems);
        rvWeatherStates.setHasFixedSize(true);
        rvWeatherStates.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWeatherStates.setAdapter(adapter);
        DataDownloader downloaderTask = new DataDownloader(524901, 5,
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
