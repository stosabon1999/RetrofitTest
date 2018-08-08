package ru.production.ssobolevsky.retrofittest.presentationlayer.general;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import ru.production.ssobolevsky.retrofittest.R;
import ru.production.ssobolevsky.retrofittest.presentationlayer.MyApplication;
import ru.production.ssobolevsky.retrofittest.presentationlayer.adapter.CustomAdapter;
import ru.production.ssobolevsky.retrofittest.presentationlayer.details.mvvm.ObservableWeather;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mManager;
    private CustomAdapter mAdapter;
    private TextView mTextView;
    @Inject
    MainPresenter mMainPresenter;

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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        MyApplication.getApplicationComponent().inject(this);
        mTextView = findViewById(R.id.tv_empty_data);
        mMainPresenter.attachView(this);
        mRecyclerView = findViewById(R.id.rv_weather);
        mSwipeRefreshLayout =  findViewById(R.id.activity_main_swipe_refresh_layout);
        mManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new CustomAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> mMainPresenter.getNewData());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
        mMainPresenter = null;
    }

    @Override
    public void showData(List<ObservableWeather> data) {
        mTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.setData(data);
    }

    @Override
    public void onRefresh() {
        mMainPresenter.getNewData();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void render(MainState mainState) {
        if (mainState.isLoading()) {
            showProgress();
        } else if (mainState.getError() != null) {
            hideProgress();
            try {
                throw mainState.getError();
            } catch (Throwable throwable) {
                Log.d("LOG", "Непредвиденная ошибка");
            }
        } else if (mainState.getObservableWeathers() != null) {
            if (mainState.getObservableWeathers().isEmpty()) {
                showText();
                hideProgress();
            } else {
                showData(mainState.getObservableWeathers());
                hideProgress();
            }
        }
    }
    @Override
    public void showText() {
        mTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }
}
