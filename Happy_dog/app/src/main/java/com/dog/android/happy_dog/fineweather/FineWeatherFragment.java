package com.dog.android.happy_dog.fineweather;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dog.android.happy_dog.MainActivity;
import com.dog.android.happy_dog.R;
import com.dog.android.happy_dog.data.FineWeatherRepository;
import com.dog.android.happy_dog.data.LocationFineWeatherRepository;
import com.dog.android.happy_dog.weather_material.FineWeather;

import java.io.IOException;
import java.util.List;

public class FineWeatherFragment extends Fragment implements FineWeatherContract.View {
    private TextView mLocationTextView;
    private TextView mTempTextView;
    private TextView mSkyTextView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FineWeatherPresenter mPresenter;
    private FineWeatherRepository mRepository;

    //Geocoder
    Geocoder coder;


    public  static FineWeatherFragment newInstance(double lat, double lng){
        Bundle args = new Bundle();
        args.putDouble("lat", lat);
        args.putDouble("lng", lng);
        FineWeatherFragment fragment = new FineWeatherFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            double lat = getArguments().getDouble("lat");
            double lng = getArguments().getDouble("lng");
            mRepository = new LocationFineWeatherRepository(lat, lng);

        } else {
            mRepository = new LocationFineWeatherRepository();
// Fragment0에서 실행이 안됨.
            ((MainActivity)getActivity()).getLastKnownLocation();
        }
        mPresenter = new FineWeatherPresenter(mRepository, this);
        mPresenter.loadFineWeatherData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fine_weather, container, false);
        mLocationTextView = view.findViewById(R.id.result_location_text);
        mSkyTextView = view.findViewById(R.id.result_sky_text);
        mTempTextView = view.findViewById(R.id.result_temp_text);

        coder = new Geocoder(getActivity());


        if (savedInstanceState != null){
            // 복원
            mLocationTextView.setText(savedInstanceState.getString("location"));
            mSkyTextView.setText(savedInstanceState.getString("sky"));
            mTempTextView.setText(savedInstanceState.getString("temp"));
        }
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mPresenter.loadFineWeatherData();
            }
        });
        return view;
    }

    // 임시저장하는 부분
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("location", mLocationTextView.getText().toString());
        outState.putString("sky", mSkyTextView.getText().toString());
        outState.putString("temp", mTempTextView.getText().toString());
    }

    ///// 결과를 보여주는 부분 // 영어 한글로 변환 //
    private String transferWeather(FineWeather fineWeather) {
        String weather0 = fineWeather.getWeather().get(0).getMain();
        String weather = weather0.toLowerCase();
        if ( weather.equalsIgnoreCase("mist")||weather.equalsIgnoreCase("Smoke")||
            weather.equalsIgnoreCase("Haze")|| weather.equalsIgnoreCase("Dust")||
            weather.equalsIgnoreCase("Fog")||weather.equalsIgnoreCase("sand")||
            weather.equalsIgnoreCase("volcanic ash")){ return "안개"; }
        else if (weather.equalsIgnoreCase("squalls")){ return "바람"; }
        else if (weather.equalsIgnoreCase("clouds")){ return "구름"; }
        else if (weather.equalsIgnoreCase("tornado")){ return "토네이도"; }
        else if (weather.equalsIgnoreCase("Clear")){ return "맑음"; }
        else if (weather.equalsIgnoreCase("Snow")){ return "눈"; }
        else if (weather.equalsIgnoreCase("Rain")){ return "비"; }
        else if (weather.equalsIgnoreCase("Drizzle")){ return "이슬비"; }
        else if (weather.equalsIgnoreCase("shower")){ return "소나기"; }
        else if (weather.equalsIgnoreCase("Thunderstorm")){ return "우박"; }
        else if (weather.equalsIgnoreCase("연무")||weather.equalsIgnoreCase("박무")){ return "안개"; }
        else if (weather.equalsIgnoreCase("온흐림")){ return "흐림"; }
        return "";
    }

    public void showFineWeatherResult(FineWeather fineWeather) {

        //영어 한글로 변환 //
        String currentWeather = transferWeather(fineWeather);
        //캘빈온도(K)에서 섭씨(°C) 구하기 (297.23K − 273.15)
        double Temp1 = fineWeather.getMain().getTemp() - 273.15;
        String Temp2 = String.format("%.1f", Temp1);


        List<Address> list = null;

        try{
            list = coder.getFromLocation(
                    Double.parseDouble(fineWeather.getCoord().getLat()),
                    Double.parseDouble(fineWeather.getCoord().getLon()),
                    10);
        } catch ( NumberFormatException e) {
            e.printStackTrace();
        } catch ( IOException e){
            mLocationTextView.setText("에러 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            Address a = list.get(0);
            if(list != null) {
                mLocationTextView.setText(a.getAdminArea() + " " + a.getThoroughfare());
            }
            //getMain//날씨 대분류값//주소값에 &lang=kr& 추가하면 한글 번역이 됨.
            mSkyTextView.setText("오늘 날씨   " + currentWeather );
            mTempTextView.setText(Temp2 + "°C" );
        } catch (Exception e) {
            mLocationTextView.setText("일일 허용량 초과");
            mSkyTextView.setText("일일 허용량 초과");
            mTempTextView.setText("일일 허용량 초과");

        }
    }
/*
               서울특별시 가산동 오늘 날씨 안개 20°C
        */

    @Override
    public void showLoadError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingStrart() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void loadingEnd() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void reload(double lat, double lng) {
        mRepository = new LocationFineWeatherRepository(lat, lng);
        mPresenter = new FineWeatherPresenter(mRepository, this);
        mPresenter.loadFineWeatherData();
    }
}
