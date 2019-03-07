package pl.pzjapp.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainFragment extends Fragment implements AsyncResultListener {
    private TextView testTextView;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //TODO Create RecyclerView with items that represents weather data

        //This is a test definition, that will be replaced by correct code.
        Button btnExample = view.findViewById(R.id.btnExample);
        testTextView = view.findViewById(R.id.text);

        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hello world!", Toast.LENGTH_LONG).show();
                DataDownloader downloaderTask = new DataDownloader(524901, 2,
                        MainFragment.this);
                downloaderTask.execute();
            }
        });
        return view;
    }

    @Override
    public void fillView(ArrayList<DataModel> data) {
        for(DataModel item : data)
        {
            testTextView.append("\nCity: " + item.getCity());
            testTextView.append("\nCountry: " + item.getCountry());
            testTextView.append("\nWeather state: " + item.getWeatherState());
            testTextView.append("\nHumidity: " + item.getHumidity());
            testTextView.append("\nPressure: " + item.getPressure());
            testTextView.append("\nTemp: " + item.getTemp());
            testTextView.append("\nTempMin: " + item.getTempMin());
            testTextView.append("\nTempMax: " + item.getTempMax());
            testTextView.append("\nWind speed: " + item.getWindSpeed());
            testTextView.append("\nIcon: " + item.getIconRef());
            testTextView.append("\nDate: " + item.getDate() + "\n");
        }
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
