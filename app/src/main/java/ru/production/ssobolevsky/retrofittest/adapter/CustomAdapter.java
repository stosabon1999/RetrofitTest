package ru.production.ssobolevsky.retrofittest.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.production.ssobolevsky.retrofittest.mvvm.ObservableWeather;
import ru.production.ssobolevsky.retrofittest.R;
import ru.production.ssobolevsky.retrofittest.databinding.ItemWeatherBinding;
import ru.production.ssobolevsky.retrofittest.WeatherActivity;

/**
 * Created by pro on 09.07.2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private List<ObservableWeather> mData;
    private Context mContext;

    public CustomAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWeatherBinding itemWeatherBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_weather, parent, false);
        return new MyViewHolder(itemWeatherBinding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ObservableWeather weather = mData.get(position);
        holder.mBinding.setWeather(weather);
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(WeatherActivity.newIntent(mContext, holder.mBinding.tvTime.getText().toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ItemWeatherBinding mBinding;

        public MyViewHolder(ItemWeatherBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public void setData(List<ObservableWeather> list) {
        mData = list;
        notifyDataSetChanged();
    }

}
