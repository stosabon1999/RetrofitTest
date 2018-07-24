package ru.production.ssobolevsky.retrofittest.domainlayer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.ForecastResponse;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;

/**
 * Created by pro on 12.07.2018.
 */

public interface ForecastRepository {

    Observable<List<WeatherResponse>> getSevenDaysForecast();

    Single<Weather> getOneDayForecast(String date);

}
