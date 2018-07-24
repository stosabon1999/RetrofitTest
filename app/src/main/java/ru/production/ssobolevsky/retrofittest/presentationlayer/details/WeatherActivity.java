package ru.production.ssobolevsky.retrofittest.presentationlayer.details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import ru.production.ssobolevsky.retrofittest.R;
import ru.production.ssobolevsky.retrofittest.datalayer.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.datalayer.repository.ForecastRepositoryImpl;
import ru.production.ssobolevsky.retrofittest.domainlayer.model.Weather;
import ru.production.ssobolevsky.retrofittest.domainlayer.usecase.OneDayForecastUseCase;
import ru.production.ssobolevsky.retrofittest.presentationlayer.MyApplication;
import ru.production.ssobolevsky.retrofittest.presentationlayer.general.MainPresenter;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    public static final String DATA = "DATA";

    private TextView mTextViewDate;
    private TextView mTextViewTemperature;
    private TextView mTextViewHumidity;
    private TextView mTextViewPressure;
    @Inject
    WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }

    private void init() {
        mTextViewDate = findViewById(R.id.tv_day);
        mTextViewTemperature = findViewById(R.id.tv_temperature_day);
        mTextViewHumidity = findViewById(R.id.tv_humidity_day);
        mTextViewPressure = findViewById(R.id.tv_pressure_day);

        MyApplication.getApplicationComponent().inject(this);
        mWeatherPresenter.attachView(WeatherActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWeatherPresenter.sendMessageToReceiveFullWeatherData(getIntent().getStringExtra(DATA));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherPresenter.detachView();
        mWeatherPresenter = null;
    }

    public static final Intent newIntent(Context context, String date) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(DATA, date);
        return intent;
    }

    @Override
    public void showWeather(Weather weather) {
        mTextViewDate.setText(weather.getTime().toString());
        mTextViewTemperature.setText(weather.getTemperature().toString());
        mTextViewHumidity.setText(weather.getHumidity().toString());
        mTextViewPressure.setText(weather.getPressure().toString());
    }

}
