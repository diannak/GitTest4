package com.dog.android.happy_dog.data;



import com.dog.android.happy_dog.util.FineWeatherUtil;
import com.dog.android.happy_dog.weather_material.FineWeather;

import retrofit2.Callback;

public class LocationFineWeatherRepository implements FineWeatherRepository {

    private FineWeatherUtil mFineWeatherUtil;
    private double mLat;
    private double mLon;

    public LocationFineWeatherRepository(){
        mFineWeatherUtil = new FineWeatherUtil();
    }

    public LocationFineWeatherRepository(double lat, double lng) {
        this();
        this.mLat = lat;
        this.mLon = lng;
    }

    @Override
    public boolean isAvailable() {
        if (mLat != 0.0 && mLon != 0.0){
            return true;
        }
        return false;
    }

    @Override
    public void getFineWeatherData(Callback<FineWeather> callback) {
        mFineWeatherUtil.getApi().getFineWeather(mLat, mLon)
                .enqueue(callback);
    }
}
