package ru.production.ssobolevsky.retrofittest.datalayer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by pro on 09.07.2018.
 */
@Dao
public interface WeatherDAO {

    @Query("SELECT * FROM weather")
    List<WeatherEntity> getListWeather();

    @Query("SELECT * FROM weather WHERE date = :date")
    Single<WeatherEntity> gerWeatherByDate(String date);

    @Query("DELETE FROM weather")
    void clearTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWeather(WeatherEntity entity);

}
