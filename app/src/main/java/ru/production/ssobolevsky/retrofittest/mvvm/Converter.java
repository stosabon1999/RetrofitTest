package ru.production.ssobolevsky.retrofittest.mvvm;

import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;

/**
 * Created by pro on 12.07.2018.
 */

public class Converter {

    public static ObservableWeather convertEntityToObservable(WeatherEntity entity) {
        return new ObservableWeather(entity.getDate(), entity.getTemperature());
    }
}
