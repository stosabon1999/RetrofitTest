package ru.production.ssobolevsky.retrofittest;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import ru.production.ssobolevsky.retrofittest.database.WeatherDatabase;
import ru.production.ssobolevsky.retrofittest.retrofit.Weather;

/**
 * Created by pro on 12.07.2018.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        WeatherDatabase.initInstance(getApplicationContext());
    }
}

