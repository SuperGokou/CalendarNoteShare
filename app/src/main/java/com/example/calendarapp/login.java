package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText Username = findViewById(R.id.Username);
        EditText Password = findViewById(R.id.Password);
        TextView loginOutput = findViewById(R.id.LoginOutput);
        Button signIn = findViewById(R.id.SignInButton);
        Button signUp = findViewById(R.id.SignUpButton);

        RequestQueue queue = Volley.newRequestQueue(this);

        signIn.setOnClickListener(view -> {
            String user = Username.getText().toString();
            String pass = Password.getText().toString();
            if(user.equals("") || Password.getText().toString().equals("")){
                loginOutput.setText("Invalid username or password.");
                return;
            }
            String url = "https://us-east-1.aws.data.mongodb-api.com/app/calender_note_share-dfhle/endpoint/api/get/onUser?";
            url = url + "user=" + user + "&";
            url = url + "pass=" + pass;

            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                    url,
                    null,
                    response -> {
                        try {
                            JSONArray res = response.getJSONArray("arr");
                            if(res.length() < 1){
                                loginOutput.setText("Invalid username or password.");
                                return;
                            }
                            loginOutput.setText("Sign In Successful.");
                            Intent nextPage = new Intent(login.this, MainActivity.class);
                            nextPage.putExtra("user", user);
                            startActivity(nextPage);
                        }
                        catch (JSONException e){
                            loginOutput.setText("Something Broke");
                            e.printStackTrace();
                        }
                    },
                    System.out::println
            );
            queue.add(getRequest);
        });
        signUp.setOnClickListener(view -> {
            loginOutput.setText("Signup");

            String user = Username.getText().toString();
            String pass = Password.getText().toString();
            if(user.equals("") || Password.getText().toString().equals("")){
                loginOutput.setText("Invalid username or password.");
                return;
            }
            String urlPost = "https://us-east-1.aws.data.mongodb-api.com/app/calender_note_share-dfhle/endpoint/api/POST/user";

            StringRequest postRequest = new StringRequest(Request.Method.POST, urlPost,
                    response -> {},
                    Throwable::printStackTrace
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> newUser = new HashMap<>();
                    newUser.put("user", user);
                    newUser.put("pass", pass);
                    return newUser;
                }
            };

            String url = "https://us-east-1.aws.data.mongodb-api.com/app/calender_note_share-dfhle/endpoint/api/get/onUser?";
            url = url + "user=" + user;
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                    url,
                    null,
                    response -> {
                        try {
                            JSONArray res = response.getJSONArray("arr");
                            if(res.length() < 1){
                                loginOutput.setText("Username Available");
                                queue.add(postRequest);
                                Intent nextPage = new Intent(login.this, MainActivity.class);
                                nextPage.putExtra("user", user);
                                startActivity(nextPage);
                                return;
                            }
                            loginOutput.setText("Username Already in use");
                        }
                        catch (JSONException e){
                            loginOutput.setText("Something Broke");
                            e.printStackTrace();
                        }
                    },
                    System.out::println
            );
            queue.add(getRequest);
        });


    }
}