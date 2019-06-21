package com.dog.android.happy_dog.data;



import com.dog.android.happy_dog.weather_material.FineWeather;

import retrofit2.Callback;

//나중에 코딩이 복잡해지지 않도록 하기 위함
public interface FineWeatherRepository {
    boolean isAvailable();
    //retrofit2에서 제공하는 Callback
    void getFineWeatherData( Callback<FineWeather> callback );

}
