package com.example.calendarapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class notelistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notelist);

        MaterialButton backBtn = findViewById(R.id.backbutton);

        RequestQueue queue = Volley.newRequestQueue(this);

        Intent prevPageIntent = getIntent();
        int year = prevPageIntent.getIntExtra("year", 0);
        int month = prevPageIntent.getIntExtra("month", 0);
        int day = prevPageIntent.getIntExtra("day", 0);

        String url = "https://us-east-1.aws.data.mongodb-api.com/app/calender_note_share-dfhle/endpoint/api/get/onDate?";
        url = url + "year=" + year + "&";
        url = url + "month=" + month + "&";
        url = url + "day=" + day;

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> notes = new ArrayList<>();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                response -> {
                    try{
                        JSONArray res = response.getJSONArray("arr");
                        for(int i = 0; i< res.length(); i++){
                            JSONObject row = res.getJSONObject(i);
                            titles.add(row.getString("title"));
                            notes.add(row.getString("noteContent"));
                        }

                        RecyclerAdapter ra = new RecyclerAdapter(titles,notes);

                        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
                        rv.setAdapter(ra);
                        rv.setLayoutManager(new LinearLayoutManager(this));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                System.out::println
        );
        queue.add(getRequest);

        backBtn.setOnClickListener(view -> startActivity(new Intent(notelistActivity.this,
                MainActivity.class)));


    }
}