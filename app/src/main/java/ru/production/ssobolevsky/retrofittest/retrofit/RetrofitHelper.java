package ru.production.ssobolevsky.retrofittest.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pro on 07.07.2018.
 */

public class RetrofitHelper {

    private static final String BASE_URL = "https://api.darksky.net/forecast/e4f70c41baa5a36c0a42b2df66c679db/";

    public ApiWeatherMap getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiWeatherMap.class);
    }

}
