package com.example.flightmate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button BuscarVueloButton = (Button) findViewById(R.id.buscar_button);
        Button LlegadasButton = (Button) findViewById(R.id.llegadas_button);
        Button SalidasButton = (Button) findViewById(R.id.salidas_button);
        Button RegistrosButton = (Button) findViewById(R.id.registrovuelos_button);



        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        BuscarVueloButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, BuscarVuelosActivity.class));
                    }});

        LlegadasButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, LlegadasActivity.class));
                    }});
        SalidasButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, SalidasActivity.class));
                    }});
        RegistrosButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, RegistrosActivity.class));
                    }});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}