package ru.production.ssobolevsky.retrofittest.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.production.ssobolevsky.retrofittest.retrofit.Weather;

/**
 * Created by pro on 09.07.2018.
 */

public class DailyWeather {

    @SerializedName("data")
    private List<Weather> mData;

    public List<Weather> getData() {
        return mData;
    }

    public void setData(List<Weather> data) {
        mData = data;
    }
}
