package com.example.flightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    View vcabecera;

    int horas,minutos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_vuelos);

        btn_airportID = findViewById(R.id.btn_getairportID);
        et_dataInput = findViewById(R.id.et_dataInput);
        viewVuelo = findViewById(R.id.viewVuelo);
        divider= findViewById(R.id.divider3);
        vcabecera = findViewById(R.id.viewcabecera);
        ImageView ticket = findViewById(R.id.imageViewIconoTicket);
        TextView airlineTextView = findViewById(R.id.textViewNumero_vuelo);
        TextView statusTextView = findViewById(R.id.textViewStatus);
        TextView duracionlTextView = findViewById(R.id.textViewduracion);

        TextView citySalidaTextView = findViewById(R.id.textViewCitySalida);
        TextView TerminaSalidalTextView = findViewById(R.id.textViewTerminalSalida);
        TextView HoraSalidaRealTextView = findViewById(R.id.textViewHoraSalida);
        TextView HoraSalidaProgramadaTextView = findViewById(R.id.textViewHoraSalidaProgramada);
        TextView PuertaSalidalTextView = findViewById(R.id.textViewPuertaSalida);

        TextView cityLlegadaTextView = findViewById(R.id.textViewCityLlegada);
        TextView TerminaLlegadalTextView = findViewById(R.id.textViewTerminalLlegada);
        TextView HoraLlegadaRealTextView = findViewById(R.id.textViewHoraLlegada);
        TextView HoraLlegadaProgramadaTextView = findViewById(R.id.textViewHoraLlegadaprogramada);
        TextView PuertaLlegadalTextView = findViewById(R.id.textViewPuertaLlegada);




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
                             String status = respuesta.getString("status");
                             String duracion = respuesta.getString("duration");

                            String citySalida = respuesta.getString("dep_city");
                            String paisSalida = respuesta.getString("dep_country");
                            String TerminalSalida = respuesta.getString("dep_terminal");
                            String HoraReal = respuesta.getString("dep_actual");
                            String HoraProgramada = respuesta.getString("dep_time");
                            String puertaSalida = respuesta.getString("dep_gate");

                             String cityLlegada = respuesta.getString("arr_city");
                             String paisLlegada= respuesta.getString("arr_country");
                             String TerminalLlegada = respuesta.getString("arr_terminal");
                             String HoraRealLlegada = respuesta.getString("arr_actual");
                             String HoraProgramadaLlegada = respuesta.getString("arr_time");
                             String puertaLlegada = respuesta.getString("arr_gate");

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


                             String arr_time_hour_minR = "";
                             String arr_time_hour_minP = "";
                             if (HoraRealLlegada != "null") {
                                 Date arr_time_dateR = format.parse(HoraRealLlegada);
                                 arr_time_hour_minR = formatHourMin.format(arr_time_dateR);
                             }
                             if (HoraProgramadaLlegada != "null") {
                                 Date arr_time_dateP = format.parse(HoraProgramadaLlegada);
                                 arr_time_hour_minP = formatHourMin.format(arr_time_dateP);
                             }



                             if(duracion == "null"){
                                 duracion ="-";
                             }

                             if(duracion != "null"){
                                 int num = Integer.parseInt(duracion);
                                  horas = num/60;
                                  minutos = num%60;
                             }

                             if(puertaLlegada == "null"){
                                 puertaLlegada ="-";
                             }
                             if(puertaSalida == "null"){
                                 puertaSalida ="-";
                             }
                             if(TerminalLlegada == "null"){
                                 TerminalLlegada ="-";
                             }
                             if(TerminalSalida == "null"){
                                 TerminalSalida ="-";
                             }



                             statusTextView.setText(status);
                             airlineTextView.setText(numero_vuelo);
                             duracionlTextView.setText("Duación: " + horas +":"+minutos+"h");

                             citySalidaTextView.setText(citySalida+"("+paisSalida+")");
                             TerminaSalidalTextView.setText("Terminal:            "+TerminalSalida);
                             HoraSalidaRealTextView.setText("Salida Confirmada:   "+dep_time_hour_minR);
                             HoraSalidaProgramadaTextView.setText("Salida Programada:   "+dep_time_hour_minP);
                             PuertaSalidalTextView.setText("Puerta:               "+puertaSalida);

                             cityLlegadaTextView.setText(cityLlegada+"("+paisLlegada+")");
                             TerminaLlegadalTextView.setText("Terminal:            "+TerminalLlegada);
                             HoraLlegadaRealTextView.setText("Llegada Confirmada:   "+arr_time_hour_minR);
                             HoraLlegadaProgramadaTextView.setText("Llegada Programada:   "+arr_time_hour_minP);
                             PuertaLlegadalTextView.setText("Puerta:               "+puertaLlegada);

                             HoraSalidaProgramadaTextView.setVisibility(View.VISIBLE);
                             HoraSalidaRealTextView.setVisibility(View.VISIBLE);
                             PuertaSalidalTextView.setVisibility(View.VISIBLE);
                             TerminaSalidalTextView.setVisibility(View.VISIBLE);
                             citySalidaTextView.setVisibility(View.VISIBLE);


                             HoraLlegadaProgramadaTextView.setVisibility(View.VISIBLE);
                             HoraLlegadaRealTextView.setVisibility(View.VISIBLE);
                             PuertaLlegadalTextView.setVisibility(View.VISIBLE);
                             TerminaLlegadalTextView.setVisibility(View.VISIBLE);
                             cityLlegadaTextView.setVisibility(View.VISIBLE);



                             airlineTextView.setVisibility(View.VISIBLE);
                             statusTextView.setVisibility(View.VISIBLE);
                             duracionlTextView.setVisibility(View.VISIBLE);
                             ticket.setVisibility(View.VISIBLE);
                             divider.setVisibility(View.VISIBLE);
                             vcabecera.setVisibility(View.VISIBLE);
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