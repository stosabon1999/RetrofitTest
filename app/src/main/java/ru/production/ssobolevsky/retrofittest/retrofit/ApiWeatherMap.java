package ru.production.ssobolevsky.retrofittest.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.production.ssobolevsky.retrofittest.retrofit.WeatherResponse;

/**
 * Created by pro on 07.07.2018.
 */

public interface ApiWeatherMap {

    @GET("{latitude},{longitude}")
    Call<WeatherResponse> getSevenDayWeather(@Path("latitude") String latitude,
                                             @Path("longitude") String longitude);

}
