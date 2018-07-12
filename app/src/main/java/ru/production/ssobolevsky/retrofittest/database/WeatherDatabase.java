package ru.production.ssobolevsky.retrofittest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ru.production.ssobolevsky.retrofittest.retrofit.Weather;

/**
 * Created by pro on 09.07.2018.
 */
@Database(entities = WeatherEntity.class, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    private static WeatherDatabase INSTANCE;

    public static WeatherDatabase getInstance() {
        return INSTANCE;
    }

    public static void initInstance(Context context) {
        INSTANCE = Room.databaseBuilder(context, WeatherDatabase.class, "my_weather.db")
                .build();
    }

    public abstract WeatherDAO getWeatherDAO();



}
