package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopOfMoviesActivity extends AppCompatActivity implements View.OnClickListener {

    int page = 1;
    int totalPages = 1;
    ListView listView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_of_movies);
        intent = getIntent();
        getPage();
    }

    private void getPage(){

        ImageButton btnPrev = findViewById(R.id.btnPrev);
        ImageButton btnNext = findViewById(R.id.btnNext);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TopMovieZapros zapros = retrofit.create(TopMovieZapros.class);
        Call<ResponseData> responseDataCall = zapros.getTopMovies("49331f13ada4d3d11cf958ec9fb38be8", Global.language, page);
        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.code() != 200){
                    Toast.makeText(TopOfMoviesActivity.this, "Какая-то ошибка :/", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                ResponseBodyAdapter adapter = new ResponseBodyAdapter(TopOfMoviesActivity.this, R.layout.trending_movie_preview_list_item, response.body().results);
                totalPages = response.body().total_pages;
                listView = findViewById(R.id.Toplist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable throwable) {
                Toast.makeText(TopOfMoviesActivity.this, "Какая-то ошибка :/", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnPrev:
                if(page > 1) {
                    page -= 1;
                    getPage();
                }
                break;
            case R.id.btnNext:
                if(page < totalPages) {
                    page += 1;
                    getPage();
                }
                break;
        }
    }
}