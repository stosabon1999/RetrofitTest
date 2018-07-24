package ru.production.ssobolevsky.retrofittest.presentationlayer;

import android.app.Application;

import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherDatabase;
import ru.production.ssobolevsky.retrofittest.injection.AppComponent;
import ru.production.ssobolevsky.retrofittest.injection.AppModule;
import ru.production.ssobolevsky.retrofittest.injection.DaggerAppComponent;

/**
 * Created by pro on 12.07.2018.
 */

public class MyApplication extends Application {

    private static AppComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(getApplicationContext()))
        .build();
    }

    public static AppComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}

