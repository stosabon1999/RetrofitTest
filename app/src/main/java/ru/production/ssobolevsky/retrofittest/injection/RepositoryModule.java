package ru.production.ssobolevsky.retrofittest.injection;

import dagger.Module;
import dagger.Provides;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherDAO;
import ru.production.ssobolevsky.retrofittest.datalayer.repository.ForecastRepositoryImpl;
import ru.production.ssobolevsky.retrofittest.datalayer.retrofit.ApiWeatherService;
import ru.production.ssobolevsky.retrofittest.domainlayer.ForecastRepository;

@Module
public class RepositoryModule {

    @Provides
    ForecastRepository provideForecastRepository(ApiWeatherService service, WeatherDAO dao) {
        return new ForecastRepositoryImpl(service, dao);
    }

}
