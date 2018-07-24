package ru.production.ssobolevsky.retrofittest.presentationlayer.details.mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ru.production.ssobolevsky.retrofittest.BR;

/**
 * Created by pro on 11.07.2018.
 */

public class ObservableWeather extends BaseObservable {

    private String mTime;
    private String mTemperature;

    public ObservableWeather(String time, String temperature) {
        mTime = time;
        mTemperature = temperature;
    }

    @Bindable
    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
        notifyPropertyChanged(BR.time);
    }

    @Bindable
    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String temperature) {
        notifyPropertyChanged(BR.temperature);
        mTemperature = temperature;
    }
}
