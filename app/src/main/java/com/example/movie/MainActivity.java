package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    ListView listView;
    String type = "day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPage();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trends, menu);
        menu.add(Menu.NONE, 0, 0, "PERIOD ").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.day:
                type = "day";
                getPage();
                break;
            case R.id.week:
                type = "week";
                getPage();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void getPage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Zapros zapros = retrofit.create(Zapros.class);
        Call<ResponseData> responseDataCall = zapros.getData(type, "49331f13ada4d3d11cf958ec9fb38be8");
        responseDataCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.code() != 200){
                    Toast.makeText(MainActivity.this, "Какая-то ошибка :/", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                ResponseBodyAdapter adapter = new ResponseBodyAdapter(MainActivity.this, R.layout.trending_movie_preview_list_item, response.body().results);
                listView = findViewById(R.id.list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Какая-то ошибка :/", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}