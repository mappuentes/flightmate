package com.example.flightmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    private EditText email,password;
    private Button register, login;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("¡Bienvenido a Flightmate!");
        setContentView(R.layout.activity_auth);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        register = findViewById(R.id.buttonRegister);
        login = findViewById(R.id.buttonLogin);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String em = email.getText().toString();
                        String pa = password.getText().toString();
                        if(TextUtils.isEmpty(em)||TextUtils.isEmpty(pa))
                        {
                            Toast.makeText(AuthActivity.this,"Error: Password and email are required.",Toast.LENGTH_SHORT).show();
                        }
                        else if(pa.length() < 6)
                        {
                            Toast.makeText(AuthActivity.this,"Error: Password needs to be longer than 6 characters",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            registerUser(em,pa);
                        }
                    }
                }
        );


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = findViewById(R.id.editTextTextEmailAddress);
                password = findViewById(R.id.editTextTextPassword);
                String edmail = email.getText().toString().trim();
                String edpassword = password.getText().toString().trim();
                if(TextUtils.isEmpty(edmail)){
                    Toast.makeText(AuthActivity.this,"Error: Email is required",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edpassword)){
                    Toast.makeText(AuthActivity.this,"Error: Password is required",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(AuthActivity.this,"Error: Password needs to be longer than 6 characters",Toast.LENGTH_SHORT).show();
                    return;
                }
                //Authenticate the user.
                auth.signInWithEmailAndPassword(edmail,edpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AuthActivity.this, "Welcome to Flightmate!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else{
                            Toast.makeText(AuthActivity.this, "Error " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void registerUser(String em, String pa) {
        auth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(
                AuthActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AuthActivity.this,"Successful registration",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AuthActivity.this, HomeActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(AuthActivity.this,"Sign up failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}