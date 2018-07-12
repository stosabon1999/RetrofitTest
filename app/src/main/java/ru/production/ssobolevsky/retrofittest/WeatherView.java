package ru.production.ssobolevsky.retrofittest;

import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;

/**
 * Created by pro on 09.07.2018.
 */

public interface WeatherView {
    void showWeather(WeatherEntity weather);
}
