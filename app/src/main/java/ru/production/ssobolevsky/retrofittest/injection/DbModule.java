package ru.production.ssobolevsky.retrofittest.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherDAO;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherDatabase;

@Module
public class DbModule {

    @Provides
    @Singleton
    WeatherDAO provideWeatherDatabase(Context context) {
        return Room.databaseBuilder(context, WeatherDatabase.class, "my_weather.db")
                .build().getWeatherDAO();
    }
}
