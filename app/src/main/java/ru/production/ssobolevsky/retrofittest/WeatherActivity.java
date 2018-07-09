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

public class WeatherActivity extends AppCompatActivity {

    public static final String DATA = "DATA";

    private TextView mTextViewDate;
    private TextView mTextViewTemperature;
    private TextView mTextViewHumidity;
    private TextView mTextViewPressure;

    private WeatherEntity mWeatherEntity;
    private WorkerThread mWorkerThread;

    private Handler mUiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WorkerThread.GET_WEATHER_DAY_RESULT) {
                mWeatherEntity = (WeatherEntity) msg.obj;
                mTextViewDate.setText(mWeatherEntity.getDate());
                mTextViewTemperature.setText(mWeatherEntity.getTemperature());
                mTextViewHumidity.setText(mWeatherEntity.getHumidity());
                mTextViewPressure.setText(mWeatherEntity.getPressure());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
        mWorkerThread = new WorkerThread(WorkerThread.NAME, getApplicationContext());
        mWorkerThread.start();
    }

    private void init() {
        mTextViewDate = findViewById(R.id.tv_day);
        mTextViewTemperature = findViewById(R.id.tv_temperature_day);
        mTextViewHumidity = findViewById(R.id.tv_humidity_day);
        mTextViewPressure = findViewById(R.id.tv_pressure_day);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Message message = new Message();
        message.what = WorkerThread.GET_WEATHER_DAY;
        message.obj = getIntent().getStringExtra(DATA);
        message.replyTo = new Messenger(mUiHandler);
        mWorkerThread.getMyHandler().sendMessage(message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWorkerThread.quit();
    }

    public static final Intent newIntent(Context context, String date) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(DATA, date);
        return intent;
    }

}
