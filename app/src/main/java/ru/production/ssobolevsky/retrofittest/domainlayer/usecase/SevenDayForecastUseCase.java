package ru.production.ssobolevsky.retrofittest.domainlayer.usecase;

import java.util.List;

import io.reactivex.Observable;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;

public interface SevenDayForecastUseCase {

    Observable<List<WeatherResponse>> getSevenDayForecast();
}
