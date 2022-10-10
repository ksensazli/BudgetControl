package com.example.budgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registry extends AppCompatActivity {

    private EditText _userName;
    private EditText _Password;
    private EditText _eMail;
    private Button _registerButton;
    private FirebaseDatabase _firebaseDb;
    private DatabaseReference _dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registry);

        _userName = findViewById(R.id.username);
        _eMail = findViewById(R.id.email);
        _Password = findViewById(R.id.password);
        _registerButton = findViewById(R.id.registerButton);

        _firebaseDb = FirebaseDatabase.getInstance();

        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _dbReference = _firebaseDb.getReference().child("Users").child(_userName.getText().toString());
                _dbReference.setValue(_Password.getText().toString());
            }
        });
    }
}