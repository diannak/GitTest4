package com.dog.android.happy_dog.util;



import com.dog.android.happy_dog.weather_material.FineWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FineWeatherApi {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";


    @GET("weather?&lang=kr&appid=8ea7ee48296dafef3efa0dc7d12590b6")
    Call<FineWeather> getFineWeather( @Query("lat") double lat,
                                      @Query("lon") double lon );


}
