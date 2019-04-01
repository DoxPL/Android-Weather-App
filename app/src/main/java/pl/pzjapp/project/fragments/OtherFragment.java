package pl.pzjapp.project.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.pzjapp.project.R;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.repository.CityRepository;

public class OtherFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public OtherFragment() {

    }


    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);

        TextView tvCities = view.findViewById(R.id.cities);
        CityRepository cityRepository = new CityRepository(getContext());
        tvCities.setText("");

        for (City city : cityRepository.getAllCities()) {
            tvCities.append(city.getId() + " " + city.getCityName() + "" + city.getCountry() + "\n\n");
        }

        return view;
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
