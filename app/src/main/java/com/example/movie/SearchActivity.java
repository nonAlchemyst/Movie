package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    int page = 1;
    int totalPages = 1;
    ListView listView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_of_movies);
        intent = getIntent();
        Search();
    }
    private void Search(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Search zapros = retrofit.create(Search.class);
        Call<ResponseData> responseDataCall = zapros.getMovies("49331f13ada4d3d11cf958ec9fb38be8", Global.language, intent.getStringExtra("Zapros"), page);
        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                ResponseBodyAdapter adapter = new ResponseBodyAdapter(SearchActivity.this, R.layout.trending_movie_preview_list_item, response.body().results);
                //page = response.body().page;
                if(response.body().total_pages == 0){
                    Toast.makeText(SearchActivity.this,"Контент отсутствует", Toast.LENGTH_LONG).show();
                    //finish();
                }
                totalPages = response.body().total_pages;
                listView = findViewById(R.id.Toplist);
                listView.setAdapter(adapter);
                //listView.setSelection(Global.firstVisibleItem);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable throwable) {

            }
        });
    }



    public void OnClick(View view){
        switch(view.getId()){
            case R.id.btnPrev:
                if(page > 1) {
                    page -= 1;
                    Search();
                }
                break;
            case R.id.btnNext:
                if(page < totalPages) {
                    page += 1;
                    Search();
                }
                break;
        }
    }
}