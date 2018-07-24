package ru.production.ssobolevsky.retrofittest.injection;

import dagger.Module;
import dagger.Provides;
import ru.production.ssobolevsky.retrofittest.domainlayer.ForecastRepository;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.OneDayForecastUseCase;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.OneDayForecastUseCaseImpl;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.SevenDayForecastUseCase;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.SevenDayForecastUseCaseImpl;

@Module
public class UseCaseModule {

    @Provides
    SevenDayForecastUseCase provideSevenDayForecastUseCase(ForecastRepository repository) {
        return new SevenDayForecastUseCaseImpl(repository);
    }
    @Provides
    OneDayForecastUseCase provideOneDayForecastUseCase(ForecastRepository repository) {
        return new OneDayForecastUseCaseImpl(repository);
    }
}
