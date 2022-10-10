package com.example.budgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalInfo extends AppCompatActivity {

    private EditText _name;
    private EditText _surname;
    private EditText _budget;
    private Button _registerButton;

    private FirebaseDatabase _firebaseDb;
    private DatabaseReference _dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        _name = findViewById(R.id.name);
        _surname = findViewById(R.id.surname);
        _budget = findViewById(R.id.budget);
        _registerButton = findViewById(R.id.registerButton);

        _firebaseDb = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        String username = intent.getExtras().getString("Username");

        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _dbReference = _firebaseDb.getReference().child("Users").child(username).child("Name");
                _dbReference.setValue(_name.getText().toString());
                _dbReference = _firebaseDb.getReference().child("Users").child(username).child("Surname");
                _dbReference.setValue(_surname.getText().toString());
                _dbReference = _firebaseDb.getReference().child("Users").child(username).child("Budget");
                _dbReference.setValue(_budget.getText().toString());
            }
        });
    }
}