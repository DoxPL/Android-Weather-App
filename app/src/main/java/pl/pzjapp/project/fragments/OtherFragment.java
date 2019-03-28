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
//        cityRepository.insertCity(new City("Uniejow", "Poland", "26.03.2019", "exmp", 1, 3.1f, 2.6f, 2.7f, 17f));
//        cityRepository.insertCity(new City("Lodz", "Poland", "27.03.2019", "exmp", 2, 3.1f, 2.6f, 2.7f, 17f));
        tvCities.setText("");

        for (City c : cityRepository.getAllCities()) {
            tvCities.append(c.getCityName() + " : " + c.getCountry() + "\n\n");
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
