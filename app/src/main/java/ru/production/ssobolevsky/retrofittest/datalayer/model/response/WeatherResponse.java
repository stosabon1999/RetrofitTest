package ru.production.ssobolevsky.retrofittest.datalayer.model.response;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pro on 07.07.2018.
 */

public class WeatherResponse {

    @SerializedName("time")
    private Long time;
    @SerializedName("temperatureHigh")
    private Double temperature;
    @SerializedName("humidity")
    private Double humidity;
    @SerializedName("pressure")
    private Double pressure;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
}
