package ru.production.ssobolevsky.retrofittest.datalayer.model.response;

import com.google.gson.annotations.SerializedName;

import ru.production.ssobolevsky.retrofittest.datalayer.model.response.DailyWeatherResponse;

/**
 * Created by pro on 07.07.2018.
 */

public class ForecastResponse {
    @SerializedName("daily")
    private DailyWeatherResponse mData;

    public DailyWeatherResponse getData() {
        return mData;
    }

    public void setData(DailyWeatherResponse data) {
        mData = data;
    }
}
