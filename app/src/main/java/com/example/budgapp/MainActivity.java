package com.example.budgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView _userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _userName = findViewById(R.id.usernameMain);

        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");

        if(username.equals("BudgApp"))
        {
            _userName.setText("Admin");
        }

        else
        {
            _userName.setText(username);
        }

    }
}