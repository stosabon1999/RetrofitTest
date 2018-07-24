package ru.production.ssobolevsky.retrofittest.presentationlayer.details;

import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;

/**
 * Created by pro on 09.07.2018.
 */

public interface WeatherView {
    void showWeather(Weather weather);
}
