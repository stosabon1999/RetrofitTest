package ru.production.ssobolevsky.retrofittest.datalayer.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import io.reactivex.Single;
import ru.production.ssobolevsky.retrofittest.datalayer.Converter;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherDAO;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherDatabase;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;
import ru.production.ssobolevsky.retrofittest.datalayer.retrofit.ApiWeatherService;
import ru.production.ssobolevsky.retrofittest.datalayer.retrofit.RetrofitConfig;
import ru.production.ssobolevsky.retrofittest.domainlayer.ForecastRepository;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;

/**
 * Created by pro on 12.07.2018.
 */

public class ForecastRepositoryImpl implements ForecastRepository {

    @Inject
    ApiWeatherService mApi;

    @Inject
    WeatherDAO mWeatherDatabase;

    public ForecastRepositoryImpl(ApiWeatherService api, WeatherDAO weatherDatabase) {
        mApi = api;
        mWeatherDatabase = weatherDatabase;
    }


    @Override
    public Observable<List<WeatherResponse>> getSevenDaysForecast() {
        Observable<List<WeatherResponse>> response = mApi.getSevenDayWeather(RetrofitConfig.LATITUDE, RetrofitConfig.LONGITUDE)
                .map(weather -> {
                    mWeatherDatabase.clearTable();
                    for (WeatherResponse weatherResponse : weather.getData().getData()) {
                        Timestamp timestamp = new Timestamp(weatherResponse.getTime() * 1000);
                        Date date = new Date(timestamp.getTime());
                        mWeatherDatabase.insertWeather(new WeatherEntity(date.toString(),
                                weatherResponse.getTemperature().toString(),
                                weatherResponse.getHumidity().toString(),
                                weatherResponse.getPressure().toString()));
                    }
                    return weather.getData().getData();
                });
        return response;
    }

    @Override
    public Single<Weather> getOneDayForecast(String date) {
        return mWeatherDatabase.gerWeatherByDate(date)
                .map(weatherEntity -> Converter.convertWeatherEntityToWeather(weatherEntity));
    }
}
