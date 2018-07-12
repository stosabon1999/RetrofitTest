package ru.production.ssobolevsky.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.retrofit.Weather;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    public static final String DATA = "DATA";

    private TextView mTextViewDate;
    private TextView mTextViewTemperature;
    private TextView mTextViewHumidity;
    private TextView mTextViewPressure;

    private MyPresenter mMyPresenter;

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

        mMyPresenter = new MyPresenter();
        mMyPresenter.attachView(WeatherActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyPresenter.sendMessageToReceiveFullWeatherData(getIntent().getStringExtra(DATA));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyPresenter.detachView();
        mMyPresenter = null;
    }

    public static final Intent newIntent(Context context, String date) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(DATA, date);
        return intent;
    }

    @Override
    public void showWeather(WeatherEntity weather) {
        mTextViewDate.setText(weather.getDate());
        mTextViewTemperature.setText(weather.getTemperature());
        mTextViewHumidity.setText(weather.getHumidity());
        mTextViewPressure.setText(weather.getPressure());
    }
}
