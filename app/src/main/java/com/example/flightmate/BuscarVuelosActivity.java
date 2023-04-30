package com.example.flightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class BuscarVuelosActivity extends AppCompatActivity {

    Button btn_airportID;
    EditText et_dataInput;

    View viewVuelo;
    View divider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_vuelos);

        btn_airportID = findViewById(R.id.btn_getairportID);
        et_dataInput = findViewById(R.id.et_dataInput);
        viewVuelo = findViewById(R.id.viewVuelo);
        divider= findViewById(R.id.divider3);
        TextView airlineTextView = findViewById(R.id.textViewNumero_vuelo);
        TextView statusTextView = findViewById(R.id.textViewStatus);
        TextView citySalidaTextView = findViewById(R.id.textViewCitySalida);
        TextView TerminaSalidalTextView = findViewById(R.id.textViewTerminalSalida);
        TextView HoraSalidaRealTextView = findViewById(R.id.textViewHoraSalida);
        TextView HoraSalidaProgramadaTextView = findViewById(R.id.textViewHoraSalidaProgramada);
        TextView PuertaSalidalTextView = findViewById(R.id.textViewPuertaSalida);



//        et_dataInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                et_dataInput.setText("");
//            }
//        });

        btn_airportID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flightNumber = et_dataInput.getText().toString();
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(BuscarVuelosActivity.this);
                String url ="https://airlabs.co/api/v9/flight?flight_iata="+flightNumber+"&api_key=e148d34f-4a9f-4ff5-bd1b-0b798d7bc488";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                         try {
                             JSONObject respuesta = response.getJSONObject("response");
                            String numero_vuelo = respuesta.getString("flight_number");
                            String citySalida = respuesta.getString("dep_city");
                            String paisSalida = respuesta.getString("dep_country");
                            String cityLlegada = respuesta.getString("arr_city");
                            String paisLlegada= respuesta.getString("arr_country");
                            String status = respuesta.getString("status");
                            String TerminalSalida = respuesta.getString("dep_terminal");
                            String HoraReal = respuesta.getString("dep_actual");
                            String HoraProgramada = respuesta.getString("dep_time");
                            String puertaSalida = respuesta.getString("dep_gate");

                             SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                             SimpleDateFormat formatHourMin = new SimpleDateFormat("HH:mm");

                             String dep_time_hour_minR = "";
                             String dep_time_hour_minP = "";
                             if (HoraReal != "null") {
                                 Date dep_time_dateR = format.parse(HoraReal);
                                 dep_time_hour_minR = formatHourMin.format(dep_time_dateR);
                             }
                             if (HoraProgramada != "null") {
                                 Date dep_time_dateP = format.parse(HoraProgramada);
                                 dep_time_hour_minP = formatHourMin.format(dep_time_dateP);
                             }


                             statusTextView.setText(status);
                             airlineTextView.setText(numero_vuelo);
                             citySalidaTextView.setText(citySalida+"("+paisSalida+")");
                             TerminaSalidalTextView.setText("Terminal:            "+TerminalSalida);
                             HoraSalidaRealTextView.setText("Salida Confirmada:   "+dep_time_hour_minR);
                             HoraSalidaProgramadaTextView.setText("Salida Programada:   "+dep_time_hour_minP);
                             PuertaSalidalTextView.setText("Puerta:               "+puertaSalida);

                             HoraSalidaProgramadaTextView.setVisibility(View.VISIBLE);
                             HoraSalidaRealTextView.setVisibility(View.VISIBLE);
                             PuertaSalidalTextView.setVisibility(View.VISIBLE);
                             TerminaSalidalTextView.setVisibility(View.VISIBLE);
                             citySalidaTextView.setVisibility(View.VISIBLE);
                             airlineTextView.setVisibility(View.VISIBLE);
                             statusTextView.setVisibility(View.VISIBLE);
                             divider.setVisibility(View.VISIBLE);
                             viewVuelo.setVisibility(View.VISIBLE);



                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {
                             throw new RuntimeException(e);
                         }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BuscarVuelosActivity.this, "error", Toast.LENGTH_SHORT).show();

                    }
                });
                queue.add(request);
            }
        });
    }
}