package ru.production.ssobolevsky.retrofittest.datalayer.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pro on 09.07.2018.
 */

public class DailyWeatherResponse {

    @SerializedName("data")
    private List<WeatherResponse> mData;

    public List<WeatherResponse> getData() {
        return mData;
    }

    public void setData(List<WeatherResponse> data) {
        mData = data;
    }
}
