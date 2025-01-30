package com.example.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView tempView = findViewById(R.id.tempView);

        int[] year = new int[1];
        int[] month = new int[1];
        int[] day = new int[1];

        Calendar currentTime = Calendar.getInstance();
        currentTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

        year[0] = currentTime.get(Calendar.YEAR);
        month[0] = currentTime.get(Calendar.MONTH) + 1;
        day[0] = currentTime.get(Calendar.DAY_OF_MONTH);


        tempView.setOnDateChangeListener((calendarView, yr, mth, dayOfMonth) -> {
            int newMonth = mth +1;
            year[0] = yr;
            month[0] = newMonth;
            day[0] = dayOfMonth;
        });//tells us when a user selects a date on the calendar


        MaterialButton addNoteBtn = findViewById(R.id.gotoaddNoteView);
        addNoteBtn.setOnClickListener(view -> {
            Intent nextPage = new Intent(MainActivity.this, noteaddActivity.class);
            nextPage.putExtra("year",year[0]);
            nextPage.putExtra("month",month[0]);
            nextPage.putExtra("day",day[0]);

            startActivity(nextPage);
        });

        Button viewNoteBtn = findViewById(R.id.goToViewNotes);
        viewNoteBtn.setOnClickListener(view -> {
            Intent nextPage = new Intent(MainActivity.this, notelistActivity.class);
            nextPage.putExtra("year",year[0]);
            nextPage.putExtra("month",month[0]);
            nextPage.putExtra("day",day[0]);

            startActivity(nextPage);
        });
        Button logout = findViewById(R.id.logOut);
        logout.setOnClickListener(view -> {
            Intent nextPage = new Intent(MainActivity.this, login.class);
            startActivity(nextPage);
        });

    }
}