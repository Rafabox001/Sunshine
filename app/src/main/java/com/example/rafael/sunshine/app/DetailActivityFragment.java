package com.example.rafael.sunshine.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.StringTokenizer;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private String forecast;
    private int position;

    private TextView date;
    private TextView date2;
    private TextView desc;
    private TextView maxTemp;
    private TextView minTemp;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private ImageView weatherImage;

    public DetailActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extra = intent.getExtras();

        forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
        position = extra.getInt("position");
        Log.v("forecast", forecast);

        date = (TextView)rootView.findViewById(R.id.list_item_forecast_date);
        date2 = (TextView)rootView.findViewById(R.id.list_item_forecast_date2);
        desc = (TextView)rootView.findViewById(R.id.list_item_forecast_desc);
        maxTemp =(TextView)rootView.findViewById(R.id.list_item_forecast_tempMax);
        minTemp =(TextView)rootView.findViewById(R.id.list_item_forecast_tempMin);
        humidity =(TextView)rootView.findViewById(R.id.humidity);
        pressure =(TextView)rootView.findViewById(R.id.pressure);
        wind =(TextView)rootView.findViewById(R.id.wind);
        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);

        StringTokenizer token = new StringTokenizer(forecast, "/");
        String d1 = token.nextToken();
        String d2 = token.nextToken();
        String des = token.nextToken();
        String tempP = token.nextToken();
        String tempM = token.nextToken();
        String press = token.nextToken();
        String speed = token.nextToken();
        String hum = token.nextToken();
        if (position == 0){
            date.setText("Today");
        }else if (position == 1){
            date.setText("Tomorrow");
        }else{
            date.setText(d1);
        }
        switch (des){
            case "Clear":
                if (position == 0){
                    weatherImage.setBackground(getActivity().getResources().getDrawable(R.drawable.art_clear));
                }else {
                    weatherImage.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_clear));
                }
                break;
            case "Rain":
                if (position == 0){
                    weatherImage.setBackground(getActivity().getResources().getDrawable(R.drawable.art_rain));
                }else {
                    weatherImage.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rain));
                }

                break;
            default:
                if (position == 0){
                    weatherImage.setBackground(getActivity().getResources().getDrawable(R.drawable.art_clear));
                }else {
                    weatherImage.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_clear));
                }
                break;
        }
        date2.setText(d2);
        desc.setText(des);
        maxTemp.setText(tempP + (char) 0x00B0);
        minTemp.setText(tempM + (char) 0x00B0);
        humidity.setText("Humidity: " + hum + " %");
        pressure.setText("Pressure: " + press + " hPa");
        wind.setText("Wind: " + speed + " km/h NW");

        return rootView;
    }
}
