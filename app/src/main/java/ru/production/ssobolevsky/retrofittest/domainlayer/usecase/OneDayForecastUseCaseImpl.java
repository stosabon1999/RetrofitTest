package ru.production.ssobolevsky.retrofittest.domainlayer.usecase;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.production.ssobolevsky.retrofittest.domainlayer.ForecastRepository;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;

public class OneDayForecastUseCaseImpl implements OneDayForecastUseCase {

    @Inject
    ForecastRepository mForecastRepository;

    public OneDayForecastUseCaseImpl(ForecastRepository forecastRepository) {
        mForecastRepository = forecastRepository;
    }


    @Override
    public Single<Weather> getOneDayForecast(String date) {
       return mForecastRepository.getOneDayForecast(date);
    }
}
