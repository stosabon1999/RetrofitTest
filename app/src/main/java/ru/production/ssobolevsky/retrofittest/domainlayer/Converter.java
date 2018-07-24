package ru.production.ssobolevsky.retrofittest.domainlayer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;
import ru.production.ssobolevsky.retrofittest.presentationlayer.details.mvvm.ObservableWeather;

/**
 * Created by pro on 12.07.2018.
 */

public class Converter {

    public static List<ObservableWeather> convertWeatherResponseToObservable(List<WeatherResponse> list) {
        List<ObservableWeather> newList = new ArrayList<>();
        for (WeatherResponse weatherResponse : list) {
            Timestamp timestamp = new Timestamp(weatherResponse.getTime() * 1000);
            java.sql.Date date = new java.sql.Date(timestamp.getTime());
            newList.add(new ObservableWeather(date.toString(), weatherResponse.getTemperature().toString()));
        }
        return newList;
    }
}
