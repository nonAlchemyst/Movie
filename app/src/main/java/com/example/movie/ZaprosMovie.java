package com.example.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ZaprosMovie {
    @GET("movie/{movie_id}/videos")
    Call<ResponseMovie> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String api_key, @Query("language") String language);
}
