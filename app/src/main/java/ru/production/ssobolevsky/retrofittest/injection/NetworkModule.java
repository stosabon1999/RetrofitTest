package ru.production.ssobolevsky.retrofittest.injection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.production.ssobolevsky.retrofittest.datalayer.retrofit.ApiWeatherService;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.darksky.net/forecast/e4f70c41baa5a36c0a42b2df66c679db/";

    @Provides
    @Singleton
    public ApiWeatherService provideApiWeatherService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiWeatherService.class);
    }
}
