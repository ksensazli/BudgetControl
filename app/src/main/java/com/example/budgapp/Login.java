package com.example.budgapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private EditText _userName;
    private EditText _Password;
    private Button _loginButton;
    private FirebaseDatabase _firebaseDb;
    private DatabaseReference _dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        _userName = findViewById(R.id.usernameLogin);
        _Password = findViewById(R.id.passwordLogin);
        _loginButton = findViewById(R.id.loginbtn);

        _firebaseDb = FirebaseDatabase.getInstance();

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _dbReference = _firebaseDb.getReference().child("Users").child(_userName.getText().toString());
                _dbReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snp : snapshot.getChildren())
                        {
                            if(Objects.equals(snp.getKey(), "Password") && _Password.getText().toString().equals(snp.getValue()))
                            {
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("username", _userName.getText().toString());
                                startActivity(intent);
                            }
                            else
                            {
                                //Check the username or password is wrong
                                //Toast.makeText(Login.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("STATE", "Failed to read", error.toException());
                    }
                });
            }
        });
    }
}