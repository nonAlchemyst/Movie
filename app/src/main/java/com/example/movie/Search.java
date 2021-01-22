package com.example.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Search {
    @GET("search/movie")
    Call<ResponseData> getMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("query") String query, @Query("page") int page);
}
