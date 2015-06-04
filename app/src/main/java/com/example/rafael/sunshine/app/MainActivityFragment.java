package com.example.rafael.sunshine.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayAdapter<String> adapter;
    private ListView forecastList;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String [] forecastData = {"Today, Sunny - 88/63",
            "Tomorrow, Cludy - 70/56",
            "Weds, Foggy - 70/40",
            "Thurs, Asterois - 75/65",
            "Friday, Heavy Rain - 65/56",
            "Saturday, Sunny - 90/68",
            "Sunday, Snow - 30/8"};

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastData));

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weekForecast);

        forecastList = (ListView)rootView.findViewById(R.id.listview_forecast);
        forecastList.setAdapter(adapter);

        return  rootView;
    }
}
