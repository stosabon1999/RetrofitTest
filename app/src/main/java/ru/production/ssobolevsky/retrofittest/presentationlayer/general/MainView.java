package ru.production.ssobolevsky.retrofittest.presentationlayer.general;

import java.util.List;

import io.reactivex.Observable;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.WeatherResponse;
import ru.production.ssobolevsky.retrofittest.presentationlayer.details.mvvm.ObservableWeather;

/**
 * Created by pro on 13.07.2018.
 */

public interface MainView {

    void showData(List<ObservableWeather> data);
    void onRefresh();
    void showProgress();
    void hideProgress();
    void render(MainState mainState);
    void showText();

}
