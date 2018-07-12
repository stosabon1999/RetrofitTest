package ru.production.ssobolevsky.retrofittest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;

import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.retrofit.Weather;

/**
 * Created by pro on 09.07.2018.
 */

public class MyPresenter {

    private WeatherView mWeatherView;
    private WorkerThread mWorkerThread;
    private WeatherEntity mWeatherEntity;

    private Handler mUiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WorkerThread.GET_WEATHER_DAY_RESULT) {
                mWeatherEntity = (WeatherEntity) msg.obj;
                mWeatherView.showWeather(mWeatherEntity);
            }
        }
    };

    public void attachView(WeatherView view) {
        this.mWeatherView = view;
        mWorkerThread = new WorkerThread(WorkerThread.NAME);
        mWorkerThread.start();
    }

    public void detachView() {
        mWeatherView = null;
        mWorkerThread.quit();
    }


    public void sendMessageToReceiveFullWeatherData(String date) {
        Message message = new Message();
        message.what = WorkerThread.GET_WEATHER_DAY;
        message.obj = date;
        message.replyTo = new Messenger(mUiHandler);
        mWorkerThread.getMyHandler().sendMessage(message);
    }

}
