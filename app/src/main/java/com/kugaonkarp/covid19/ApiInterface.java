package com.kugaonkarp.covid19;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v2/countries")
    Call<List<CovidModel>> getCountriesData();

    @GET("v2/all")
    Call<CovidModel> getGlobalData();
}
