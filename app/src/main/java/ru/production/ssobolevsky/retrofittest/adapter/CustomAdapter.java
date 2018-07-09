package ru.production.ssobolevsky.retrofittest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.production.ssobolevsky.retrofittest.R;
import ru.production.ssobolevsky.retrofittest.database.WeatherEntity;
import ru.production.ssobolevsky.retrofittest.retrofit.Weather;
import ru.production.ssobolevsky.retrofittest.WeatherActivity;

/**
 * Created by pro on 09.07.2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private List<WeatherEntity> mData;
    private Context mContext;

    public CustomAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_weather, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mTime.setText(String.valueOf(mData.get(position).getDate()));
        holder.mTempersature.setText(String.valueOf(mData.get(position).getTemperature()));
        holder.mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(WeatherActivity.newIntent(mContext, holder.mTime.getText().toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTime;
        private TextView mTempersature;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.tv_time);
            mTempersature = itemView.findViewById(R.id.tv_temperature);
        }
    }

    public void setData(List<WeatherEntity> list) {
        mData = list;
        notifyDataSetChanged();
    }

}
