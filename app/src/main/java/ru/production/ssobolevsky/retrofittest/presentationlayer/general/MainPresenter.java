package ru.production.ssobolevsky.retrofittest.presentationlayer.general;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.production.ssobolevsky.retrofittest.datalayer.repository.ForecastRepositoryImpl;
import ru.production.ssobolevsky.retrofittest.domainlayer.Converter;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.SevenDayForecastUseCase;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.SevenDayForecastUseCaseImpl;

/**
 * Created by pro on 13.07.2018.
 */

public class MainPresenter {

    private MainView mMainView;
    @Inject
    SevenDayForecastUseCase mSevenDayForecastUseCase;

    public MainPresenter(SevenDayForecastUseCase sevenDayForecastUseCase) {
        mSevenDayForecastUseCase = sevenDayForecastUseCase;
    }

    public void attachView(MainView view) {
        this.mMainView = view;
    }

    public void detachView() {
        mMainView = null;
    }

    public void getNewData() {
        mMainView.render(new MainState(true, null, null));
        mSevenDayForecastUseCase.getSevenDayForecast()
                .subscribeOn(Schedulers.io())
                .map(weather -> Converter.convertWeatherResponseToObservable(weather))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                    if (mMainView != null) {
                        mMainView.render(new MainState(false, weather, null));
                    } else  {
                        mMainView.render(new MainState(false, null, new NullPointerException()));
                    }
                });
    }
}
