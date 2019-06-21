package com.dog.android.happy_dog.fineweather;


import com.dog.android.happy_dog.weather_material.FineWeather;

public class FineWeatherContract {
    public interface View {
        void showFineWeatherResult( FineWeather fineWeather );
        void showLoadError( String message );
        void loadingStrart();
        void loadingEnd();
        void reload( double lat, double lng );
    }

    public interface  UserActionsListener{
        void loadFineWeatherData();
    }

}
