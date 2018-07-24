package ru.production.ssobolevsky.retrofittest.datalayer;

import java.util.ArrayList;
import java.util.List;

import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;

/**
 * Created by pro on 12.07.2018.
 */

public class Converter {

    public static Weather convertWeatherEntityToWeather(WeatherEntity entity) {
        return new Weather(entity.getDate(),
                entity.getTemperature(),
                entity.getHumidity(),
                entity.getPressure());
    }

    public static WeatherEntity convertListWeatherResponseToWeatherEntity(WeatherResponse weatherResponse) {
        return new WeatherEntity(weatherResponse.getTime().toString(),
                weatherResponse.getTemperature().toString(),
                weatherResponse.getHumidity().toString(),
                weatherResponse.getPressure().toString());
    }

}
