package com.example.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ResponseBodyAdapter extends ArrayAdapter {

    int resource;
    Context context;
    ArrayList<ResponseBody> responseBodies = new ArrayList<ResponseBody>();

    public ResponseBodyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ResponseBody> objects) {
        super(context, resource, objects);
        this.resource = resource;
        responseBodies = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(resource, null);
        }
        final ResponseBody body = responseBodies.get(position);
        if(body.title != null)
            ((TextView) convertView.findViewById(R.id.title)).setText(body.title);//"Title: "+
        else if(body.original_title != null)
            ((TextView) convertView.findViewById(R.id.title)).setText(body.original_title);//"Title: "+
        else
            ((TextView) convertView.findViewById(R.id.title)).setText("Название отсутствует");
        //String url = "https://image.tmdb.org/t/p/w500"+body.poster_path;
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+body.poster_path).into((ImageView)convertView.findViewById(R.id.poster));
        //Picasso.get().load("https://image.tmdb.org/t/p/w500"+body.backdrop_path).into((ImageView)convertView.findViewById(R.id.backgroundimage));
        ((TextView) convertView.findViewById(R.id.vote_average)).setText(Float.toString(body.vote_average));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExpandedInfoActivity.class);
                Global.setBody(body);
                context.startActivity(intent);

            }
        });
        return convertView;
    }


}
