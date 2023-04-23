package com.example.flightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class LlegadasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llegadas);
        TableLayout tableLayout = findViewById(R.id.tableDepartures);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));
        Date madridFecha = new Date();

        // Debuggear la fecha actual de Madrid.
        // Toast.makeText(LlegadasActivity.this, "Fecha actual:"+ madridFecha, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(LlegadasActivity.this);

        String url ="https://airlabs.co/api/v9/schedules?arr_iata=MAD&api_key=e148d34f-4a9f-4ff5-bd1b-0b798d7bc488";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                JsonRequestResponse json_request_response = gson.fromJson(String.valueOf(response), JsonRequestResponse.class);

                for (int i = 0; i < json_request_response.getResponse().size(); i++)
                {
                    String arr_time = json_request_response.getResponse().get(i).getArrTime();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        Date arr_time_date = format.parse(arr_time);
                        if (arr_time_date.compareTo(madridFecha) > 0 ){

                            TableRow tableRow = new TableRow(LlegadasActivity.this);

                            SimpleDateFormat formatHourMin = new SimpleDateFormat("HH:mm");
                            String arr_time_hour_min = formatHourMin.format(arr_time_date);
                            TextView text_arr_time_hour_min = new TextView(LlegadasActivity.this);
                            text_arr_time_hour_min.setText(arr_time_hour_min);

                            TextView text_flight_iata = new TextView(LlegadasActivity.this);
                            text_flight_iata.setText(json_request_response.getResponse().get(i).getFlightIata());

                            TextView text_DepIata = new TextView(LlegadasActivity.this);
                            text_DepIata.setText(json_request_response.getResponse().get(i).getDepIata());

                            TextView text_AirlineIata = new TextView(LlegadasActivity.this);
                            text_AirlineIata.setText(json_request_response.getResponse().get(i).getAirlineIata());

                            TextView text_AircraftIcao = new TextView(LlegadasActivity.this);
                            text_AircraftIcao.setText(json_request_response.getResponse().get(i).getAircraftIcao());

                            TextView text_Status = new TextView(LlegadasActivity.this);
                            text_Status.setText(json_request_response.getResponse().get(i).getStatus());

                            tableRow.addView(text_arr_time_hour_min);
                            tableRow.addView(text_flight_iata);
                            tableRow.addView(text_DepIata);
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
                Toast.makeText(LlegadasActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(request);


        //click listener
    }
}