package ru.production.ssobolevsky.retrofittest.datalayer.retrofit;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.production.ssobolevsky.retrofittest.datalayer.model.response.ForecastResponse;
import ru.production.ssobolevsky.retrofittest.presentationlayer.details.mvvm.ObservableWeather;

/**
 * Created by pro on 07.07.2018.
 */

public interface ApiWeatherService {

    @GET("{latitude},{longitude}")
    Observable<ForecastResponse> getSevenDayWeather(@Path("latitude") String latitude,
                                                    @Path("longitude") String longitude);

}
