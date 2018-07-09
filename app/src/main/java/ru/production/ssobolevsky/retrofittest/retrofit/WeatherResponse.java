package ru.production.ssobolevsky.retrofittest.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pro on 07.07.2018.
 */

public class WeatherResponse {
    @SerializedName("daily")
    private DailyWeather mData;

    public DailyWeather getData() {
        return mData;
    }

    public void setData(DailyWeather data) {
        mData = data;
    }
}
