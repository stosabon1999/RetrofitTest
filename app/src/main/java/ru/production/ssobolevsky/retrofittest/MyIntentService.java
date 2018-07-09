package ru.production.ssobolevsky.retrofittest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import ru.production.ssobolevsky.retrofittest.database.WeatherDatabase;
import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.retrofit.ApiMapper;
import ru.production.ssobolevsky.retrofittest.retrofit.RetrofitHelper;
import ru.production.ssobolevsky.retrofittest.retrofit.Weather;

public class MyIntentService extends IntentService {

    private static final String LATITUDE = "55.751244";
    private static final String LONGITUDE = "37.618423";
    private static final int MILLISECONDS = 1000;
    public static final String FILTER_WEEKLY_WEATHER = "WEATHER";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ApiMapper mapper = new ApiMapper(new RetrofitHelper());
        try {
            List<Weather> data = mapper.weatherSync(LATITUDE, LONGITUDE);
            for (Weather weather : data) {
                Timestamp timestamp = new Timestamp(weather.getTime() * MILLISECONDS);
                Date date = new Date(timestamp.getTime());
                WeatherDatabase.getInstance(getApplicationContext()).getWeatherDAO().insertWeather(new WeatherEntity(date.toString(),
                        weather.getTemperature().toString(),
                        weather.getHumidity().toString(),
                        weather.getPressure().toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent broadcastIntent = new Intent(FILTER_WEEKLY_WEATHER);
        sendBroadcast(broadcastIntent);
    }


    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        return intent;
    }

}
