package com.example.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Zapros {
    @GET("trending/all/{type}")
    Call<ResponseData> getData(@Path("type") String type, @Query("api_key") String apiKey);
}
