package ru.production.ssobolevsky.retrofittest.datalayer.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by pro on 09.07.2018.
 */
@Entity(tableName = "weather")
public class WeatherEntity {

    @PrimaryKey
    @NonNull
    String date;
    String temperature;
    String humidity;
    String pressure;

    public WeatherEntity(String date, String temperature, String humidity, String pressure) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }
}
