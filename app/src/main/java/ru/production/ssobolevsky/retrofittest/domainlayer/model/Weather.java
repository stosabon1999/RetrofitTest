package ru.production.ssobolevsky.retrofittest.domainlayer.model;

/**
 * Created by pro on 12.07.2018.
 */

public class Weather {

    private String time;
    private String temperature;
    private String humidity;
    private String pressure;

    public Weather(String time, String temperature, String humidity, String pressure) {
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
