package com.example.englishlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView Abde, RegisterUser;
    private EditText editTextFullName, editAge, editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;

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
            editTextEmail.setError("please provide a valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("min password length is 6");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        User user = new User(full_name, age, email);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterUser.this, "User has successfully signed in",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(RegisterUser.this, "failed to register", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }
}