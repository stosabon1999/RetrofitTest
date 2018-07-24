package ru.production.ssobolevsky.retrofittest.injection;

import javax.inject.Singleton;

import dagger.Component;
import ru.production.ssobolevsky.retrofittest.presentationlayer.details.WeatherActivity;
import ru.production.ssobolevsky.retrofittest.presentationlayer.general.MainActivity;

@Component(modules = {NetworkModule.class, AppModule.class, PresenterModule.class, UseCaseModule.class, RepositoryModule.class, DbModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity activity);
    void inject(WeatherActivity activity);
}
