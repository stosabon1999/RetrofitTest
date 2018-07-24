package ru.production.ssobolevsky.retrofittest.domainlayer.usecase;

import io.reactivex.Single;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;

public interface OneDayForecastUseCase {

    Single<Weather> getOneDayForecast(String date);
}
