package com.example.movie;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpandedInfoActivity extends AppCompatActivity{

    String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_info);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZaprosMovie zaprosMovie = retrofit.create(ZaprosMovie.class);
        Call<ResponseMovie> responseMovieCall = zaprosMovie.getMovie(Global.body.id, "49331f13ada4d3d11cf958ec9fb38be8", Global.language);
        responseMovieCall.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                if(response.code() == 200){
                    if(response.body().results.size()>0)
                    {
                        //Toast.makeText(ExpandedInfoActivity.this,("https://www.youtube.com/watch?v="+response.body().results.get(0).key).toString(),Toast.LENGTH_SHORT);
                        URL = "https://www.youtube.com/watch?v="+response.body().results.get(0).key;

                    }
                        //Toast.makeText(ExpandedInfoActivity.this,("https://www.youtube.com/watch?v="+response.body().results.get(0).key).toString(),Toast.LENGTH_SHORT);




                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable throwable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isEmpty((TextView) findViewById(R.id.title),Global.body.title, "Title: ");
        //((TextView) findViewById(R.id.title)).setText("Title: "+Global.body.title);
        isEmpty((TextView) findViewById(R.id.original_language), Global.body.original_language, "Original language: ");
        //((TextView) findViewById(R.id.original_language)).setText("Original language: "+Global.body.original_language);
        isEmpty((TextView) findViewById(R.id.original_title), Global.body.original_title, "Original title: ");
        //((TextView) findViewById(R.id.original_title)).setText("Original title: "+Global.body.original_title);
        isEmpty((TextView) findViewById(R.id.vote_average), Global.body.vote_average, "Vote average: ");
        //((TextView) findViewById(R.id.vote_average)).setText("Vote average: "+Float.toString(Global.body.vote_average));
        isEmpty((TextView) findViewById(R.id.overview), Global.body.overview, "Overview: \n");
        //((TextView) findViewById(R.id.overview)).setText("Overview: \n"+Global.body.overview);
        isEmpty((TextView) findViewById(R.id.vote_count), Global.body.vote_count, "Vote count: ");
        //((TextView) findViewById(R.id.vote_count)).setText("Vote count: "+Integer.toString(Global.body.vote_count));
        isEmpty((TextView) findViewById(R.id.adult), Global.body.adult, "Is adult: ");
        //((TextView) findViewById(R.id.adult)).setText("Is adult: "+Boolean.toString(Global.body.adult));
        isEmpty((TextView) findViewById(R.id.release_date), Global.body.release_date, "Release date: ");
        isEmpty((TextView) findViewById(R.id.id), Global.body.id, "Id: ");
        //((TextView) findViewById(R.id.id)).setText("Id: "+Integer.toString(Global.body.id));
        isEmpty((TextView) findViewById(R.id.popularity), Global.body.popularity, "Popularity: ");
        //((TextView) findViewById(R.id.popularity)).setText("Popularity: "+Float.toString(Global.body.popularity));
        isEmpty((TextView) findViewById(R.id.media_type), Global.body.media_type, "Media type: ");
        //((TextView) findViewById(R.id.media_type)).setText("Media type: "+Global.body.media_type);
    }

    private void isEmpty(TextView view, Object object,String message){
        if(object == null){
            view.setVisibility(View.GONE);
        }
        else{
            view.setText(message+object.toString());
        }
    }

    public void OnClick(View view){
        if(URL == "")
            Toast.makeText(ExpandedInfoActivity.this, "Видео отсутствует", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(ExpandedInfoActivity.this, PlayVideoActivity.class);
            intent.putExtra("url",URL);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}