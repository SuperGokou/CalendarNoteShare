package com.example.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;


public class noteaddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteadd);

        Intent prevPageIntent = getIntent();
        int year = prevPageIntent.getIntExtra("year", 0);
        int month = prevPageIntent.getIntExtra("month", 0);
        int day = prevPageIntent.getIntExtra("day", 0);

        EditText titleString = findViewById(R.id.titleInput);
        EditText contentString = findViewById(R.id.content);
        MaterialButton saveBtn = findViewById(R.id.savenotebutton);

        RequestQueue queue = Volley.newRequestQueue(this);
        saveBtn.setOnClickListener(view -> {

            String title = titleString.getText().toString();
            String content = contentString.getText().toString();

            if(title.equals("") || content.equals("")){
                return;
            }

            String url = "https://us-east-1.aws.data.mongodb-api.com/app/calender_note_share-dfhle/endpoint/api/POST/insert";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    response -> {},
                    Throwable::printStackTrace
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> newNote = new HashMap<>();
                    newNote.put("title", title);
                    newNote.put("noteContent", content);
                    newNote.put("year",  Integer.toString(year));
                    newNote.put("month",  Integer.toString(month));
                    newNote.put("day",  Integer.toString(day));
                    return newNote;
                }
            };
            queue.add(postRequest);

            Intent nextPage = new Intent(noteaddActivity.this, notelistActivity.class);
            nextPage.putExtra("year",year);
            nextPage.putExtra("month",month);
            nextPage.putExtra("day",day);

            startActivity(nextPage);

        });

        Button discard = findViewById(R.id.discardBtn);
        discard.setOnClickListener(view -> {
            finish();
        });


    }




}

