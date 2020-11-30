package com.example.englishlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView Abde, RegisterUser;
    private EditText editTextFullName, editAge, editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;
    private String TAG;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        Abde = (TextView) findViewById(R.id.english_with_abde);
        Abde.setOnClickListener(this);

        RegisterUser = (Button) findViewById(R.id.register_btn);
        RegisterUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.full_name);
        editAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.english_with_abde:
                break;
            case R.id.register_btn:
                RegisterUser();
        }

    }

    private void RegisterUser() {
        String email = editTextEmail.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String full_name = editTextFullName.getText().toString().trim();

        if (full_name.isEmpty()) {
            editTextFullName.setError("full name is required!");
            editTextFullName.requestFocus();
            return;
        }
        if (age.isEmpty() ) {
            editAge.setError("age is required!");
            editAge.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("please provide a correct email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("min password length is 6");
            editTextPassword.requestFocus();
            return;
        }
    }
    public void register(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}