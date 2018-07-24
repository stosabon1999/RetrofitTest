package ru.production.ssobolevsky.retrofittest.injection;

import dagger.Module;
import dagger.Provides;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.OneDayForecastUseCase;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.SevenDayForecastUseCase;
import ru.production.ssobolevsky.retrofittest.presentationlayer.details.WeatherPresenter;
import ru.production.ssobolevsky.retrofittest.presentationlayer.general.MainPresenter;

@Module
public class PresenterModule {

    @Provides
    MainPresenter provideMainPresenter(SevenDayForecastUseCase useCase) {
        return new MainPresenter(useCase);
    }

    @Provides
    WeatherPresenter provideWeatherPresenter(OneDayForecastUseCase useCase) {
        return new WeatherPresenter(useCase);
    }
}
