package ru.production.ssobolevsky.retrofittest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.production.ssobolevsky.retrofittest.adapter.CustomAdapter;
import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.mvvm.Converter;
import ru.production.ssobolevsky.retrofittest.mvvm.ObservableWeather;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mManager;
    private CustomAdapter mAdapter;
    private WorkerThread mWorkerThread;
    private MyBroadcastReceiver mMyBroadcasteceiver = new MyBroadcastReceiver();

    private Handler mUiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WorkerThread.GET_WEATHER_RESULT) {
                List<WeatherEntity> weatherEntityList = (List<WeatherEntity>) msg.obj;
                List<ObservableWeather> data = new ArrayList<>();
                for (WeatherEntity weatherEntity : weatherEntityList) {
                    data.add(Converter.convertEntityToObservable(weatherEntity));
                }
                mAdapter.setData(data);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mMyBroadcasteceiver, new IntentFilter(MyIntentService.FILTER_WEEKLY_WEATHER));
        getWeeklyWeather();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mMyBroadcasteceiver);
    }

    /**
     * Send message to {@link WorkerThread} to get weather from {@link ru.production.ssobolevsky.retrofittest.database.WeatherDatabase}.
     */
    private void getWeeklyWeather() {
        Message message = new Message();
        message.what = WorkerThread.GET_WEATHER;
        message.replyTo = new Messenger(mUiHandler);
        mWorkerThread.getMyHandler().sendMessage(message);
    }

    private void init() {
        mRecyclerView = findViewById(R.id.rv_weather);
        mSwipeRefreshLayout =  findViewById(R.id.activity_main_swipe_refresh_layout);
        mManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new CustomAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mWorkerThread = new WorkerThread(WorkerThread.NAME);
        mWorkerThread.start();
    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startService(MyIntentService.newIntent(MainActivity.this));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWorkerThread.quit();
    }

    /**
     * Broadcast receiver to react to refresh.
     */
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            getWeeklyWeather();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
