package com.example.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopMovieZapros {
    @GET("movie/top_rated")
    Call<ResponseData> getTopMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("page") int page);
}
