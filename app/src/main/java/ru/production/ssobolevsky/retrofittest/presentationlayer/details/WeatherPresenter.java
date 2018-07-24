package ru.production.ssobolevsky.retrofittest.presentationlayer.details;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.OneDayForecastUseCase;

/**
 * Created by pro on 09.07.2018.
 */

public class WeatherPresenter {

    private WeatherView mWeatherView;
    @Inject
    OneDayForecastUseCase mOneDayForecastUseCase;

    public WeatherPresenter(OneDayForecastUseCase oneDayForecastUseCase) {
        mOneDayForecastUseCase = oneDayForecastUseCase;
    }

    public void attachView(WeatherView view) {
        this.mWeatherView = view;
    }

    public void detachView() {

    }


    public void sendMessageToReceiveFullWeatherData(String date) {
        mOneDayForecastUseCase.getOneDayForecast(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> mWeatherView.showWeather(weather));
    }

}
