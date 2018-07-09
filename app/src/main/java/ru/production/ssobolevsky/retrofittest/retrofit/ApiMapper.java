package ru.production.ssobolevsky.retrofittest.retrofit;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by pro on 07.07.2018.
 */

public class ApiMapper {

    private RetrofitHelper mHelper;

    public ApiMapper(RetrofitHelper helper) {
        mHelper = helper;
    }

    /**
     * Method to get forecast of weather on week synchronously in place with given latitude and longitude.
     * @param latitude - latitude of place.
     * @param longitude - longitude of place.
     * @return list consists of 7 day forecast weather.
     * @throws IOException
     */
    public List<Weather> weatherSync(String latitude, String longitude) throws IOException {
        Response<WeatherResponse> response = mHelper.getApi().getSevenDayWeather(latitude, longitude).execute();
        return response.body().getData().getData();
    }

}
