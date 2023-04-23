package com.example.flightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SalidasActivity extends AppCompatActivity {

    private String codAeropuerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salidas);
        TableLayout tableLayout = findViewById(R.id.tablaSalidas);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.texto_salidas);
        String[] options = {"Madrid-Barajas", "Barcelona-El Prat", "Opción 3", "Opción 4", "Opción 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, options);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                setCodigoAeropuerto(selectedItem);
                Toast.makeText(getApplicationContext(), "Has seleccionado: " + codAeropuerto, Toast.LENGTH_SHORT).show();

                TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));
                Date madridFecha = new Date();

                // Debuggear la fecha actual de Madrid.
                // Toast.makeText(LlegadasActivity.this, "Fecha actual:"+ madridFecha, Toast.LENGTH_SHORT).show();
                RequestQueue queue = Volley.newRequestQueue(SalidasActivity.this);

                String url = "https://airlabs.co/api/v9/schedules?dep_iata="+codAeropuerto+"&api_key=e148d34f-4a9f-4ff5-bd1b-0b798d7bc488";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        JsonRequestResponse json_request_response = gson.fromJson(String.valueOf(response), JsonRequestResponse.class);

                        for (int i = 0; i < json_request_response.getResponse().size(); i++) {
                            String depTime = json_request_response.getResponse().get(i).getDepTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            try {
                                Date dep_time_date = format.parse(depTime);
                                if (dep_time_date.compareTo(madridFecha) > 0) {

                                    TableRow tableRow = new TableRow(SalidasActivity.this);

                                    SimpleDateFormat formatHourMin = new SimpleDateFormat("HH:mm");
                                    String dep_time_hour_min = formatHourMin.format(dep_time_date);
                                    TextView text_dep_time_hour_min = new TextView(SalidasActivity.this);
                                    text_dep_time_hour_min.setText(dep_time_hour_min);

                                    TextView text_flight_iata = new TextView(SalidasActivity.this);
                                    text_flight_iata.setText(json_request_response.getResponse().get(i).getFlightIata());

                                    TextView text_ArrIata = new TextView(SalidasActivity.this);
                                    text_ArrIata.setText(json_request_response.getResponse().get(i).getArrIata());

                                    TextView text_AirlineIata = new TextView(SalidasActivity.this);
                                    text_AirlineIata.setText(json_request_response.getResponse().get(i).getAirlineIata());

                                    TextView text_AircraftIcao = new TextView(SalidasActivity.this);
                                    text_AircraftIcao.setText(json_request_response.getResponse().get(i).getAircraftIcao());

                                    TextView text_Status = new TextView(SalidasActivity.this);
                                    text_Status.setText(json_request_response.getResponse().get(i).getStatus());

                                    tableRow.addView(text_dep_time_hour_min);
                                    tableRow.addView(text_flight_iata);
                                    tableRow.addView(text_ArrIata);
                                    tableRow.addView(text_AirlineIata);
                                    tableRow.addView(text_AircraftIcao);
                                    tableRow.addView(text_Status);

                                    tableLayout.addView(tableRow);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SalidasActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }
                });
                queue.add(request);
            }
            });

    }

    private void setCodigoAeropuerto(String selectecitem){
        switch (selectecitem){
            case ("Madrid-Barajas"):
                this.codAeropuerto="MAD";
                break;
            case ("Barcelona-El Prat"):
                this.codAeropuerto="BCN";
                break;

        }
    }
}