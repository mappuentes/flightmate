package com.example.flightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button backButton = (Button) findViewById(R.id.back_button);
        Button BuscarVueloButton = (Button) findViewById(R.id.buscar_button);

        BuscarVueloButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, BuscarVuelosActivity.class));
                    }});

        backButton.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AuthActivity.class));
            }});


    }

}