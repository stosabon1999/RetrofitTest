package ru.production.ssobolevsky.retrofittest.datalayer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by pro on 09.07.2018.
 */
@Database(entities = WeatherEntity.class, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDAO getWeatherDAO();

}
