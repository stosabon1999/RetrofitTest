package ru.production.ssobolevsky.retrofittest.domainlayer.usecase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;
import ru.production.ssobolevsky.retrofittest.domainlayer.ForecastRepository;

/**
 * Created by pro on 12.07.2018.
 */

public class SevenDayForecastUseCaseImpl implements SevenDayForecastUseCase{
    @Inject
    ForecastRepository mForecastRepository;

    public SevenDayForecastUseCaseImpl(ForecastRepository forecastRepository) {
        mForecastRepository = forecastRepository;
    }

    @Override
    public Observable<List<WeatherResponse>> getSevenDayForecast() {
        return mForecastRepository.getSevenDaysForecast();
    }

}
