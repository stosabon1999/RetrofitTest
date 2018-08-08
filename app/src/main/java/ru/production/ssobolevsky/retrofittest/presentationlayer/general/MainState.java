package ru.production.ssobolevsky.retrofittest.presentationlayer.general;

import java.util.List;

import ru.production.ssobolevsky.retrofittest.presentationlayer.details.mvvm.ObservableWeather;

public class MainState {

    private boolean isLoading;
    private List<ObservableWeather> mObservableWeathers;
    private Throwable error;

    public MainState(boolean isLoading, List<ObservableWeather> observableWeathers, Throwable error) {
        this.isLoading = isLoading;
        mObservableWeathers = observableWeathers;
        this.error = error;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public List<ObservableWeather> getObservableWeathers() {
        return mObservableWeathers;
    }

    public Throwable getError() {
        return error;
    }
}
