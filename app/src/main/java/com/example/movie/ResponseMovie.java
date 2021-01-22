package com.example.movie;

import java.util.ArrayList;

import retrofit2.http.Query;

public class ResponseMovie {
    int id;
    ArrayList<ResponseMovieBody> results;
}
