package ru.production.ssobolevsky.retrofittest;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import ru.production.ssobolevsky.retrofittest.database.WeatherDatabase;

/**
 * Created by pro on 09.07.2018.
 */

public class WorkerThread extends HandlerThread {

    public static final int GET_WEATHER = 1;
    public static final int GET_WEATHER_RESULT = 2;
    public static final int GET_WEATHER_DAY = 3;
    public static final int GET_WEATHER_DAY_RESULT = 4;
    public static final String NAME = "NAME";

    private MyHandler mMyHandler;

    public Handler getMyHandler() {
        return mMyHandler;
    }


    @Override
    protected void onLooperPrepared() {
        mMyHandler = new MyHandler(getLooper());
    }


    public WorkerThread(String name) {
        super(name);
    }


    private class MyHandler extends Handler {

        public MyHandler() {
        }

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_WEATHER :
                    Message message = new Message();
                    message.what = GET_WEATHER_RESULT;
                    message.obj = WeatherDatabase.getInstance()
                            .getWeatherDAO()
                            .getListWeather();
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case GET_WEATHER_DAY :
                    Message message1 = new Message();
                    message1.what = GET_WEATHER_DAY_RESULT;
                    message1.obj = WeatherDatabase.getInstance()
                            .getWeatherDAO()
                            .gerWeatherByDate((String) msg.obj);
                    try {
                        msg.replyTo.send(message1);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

}
