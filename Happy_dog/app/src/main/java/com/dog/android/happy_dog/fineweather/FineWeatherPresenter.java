package com.dog.android.happy_dog.fineweather;



import com.dog.android.happy_dog.data.FineWeatherRepository;
import com.dog.android.happy_dog.weather_material.FineWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FineWeatherPresenter implements FineWeatherContract.UserActionsListener {
    private final FineWeatherRepository mRepository;
    private final FineWeatherContract.View mView;

    public FineWeatherPresenter(FineWeatherRepository repository, FineWeatherContract.View view) {
        this.mRepository = repository;
        this.mView = view;
    }

    @Override
    public void loadFineWeatherData() {
        if (mRepository.isAvailable()){
            mView.loadingStrart();
            mRepository.getFineWeatherData(new Callback<FineWeather>() {
                @Override
                public void onResponse(Call<FineWeather> call, Response<FineWeather> response) {
                    //키값과 관련된 부분.(받은 결과값을 표시하는 부분)
                    mView.showFineWeatherResult(response.body());
                    mView.loadingEnd();
                }

                @Override
                public void onFailure(Call<FineWeather> call, Throwable t) {
                    mView.showLoadError(t.getLocalizedMessage());
                    mView.loadingEnd();
                }
            });
        }
    }
}
