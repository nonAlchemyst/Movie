package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Retrofit;

public class GeneralMenuActivity extends AppCompatActivity{

    CardView cardView;
    EditText zapros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_menu);
        cardView = findViewById(R.id.searchPannel);
        zapros = findViewById(R.id.nameMovie);
    }

    public void OnClick(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.btnTop:
                intent = new Intent(this, TopOfMoviesActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTrend:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.search:
                if(cardView.getVisibility() == View.GONE)
                    cardView.setVisibility(View.VISIBLE);
                else
                    cardView.setVisibility(View.GONE);
                break;
            case R.id.btnSearch:
                intent = new Intent(this, SearchActivity.class);
                intent.putExtra("Zapros", zapros.getText().toString());
                startActivity(intent);
                break;
            case R.id.lang:
                Global.changeLang();
                Toast.makeText(this, "Language is " + Global.language, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}