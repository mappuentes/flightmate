package com.example.flightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class BuscarVuelosActivity extends AppCompatActivity {

    Button btn_airportID;
    EditText et_dataInput;
    ListView lv_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_vuelos);

        btn_airportID = findViewById(R.id.btn_getairportID);
        et_dataInput = findViewById(R.id.et_dataInput);


        //click listener

        btn_airportID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(BuscarVuelosActivity.this);
                String url ="https://airlabs.co/api/v9/schedules?arr_iata=MAD&api_key=e148d34f-4a9f-4ff5-bd1b-0b798d7bc488";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        try {
                            Gson gson = new Gson();
                            JsonRequestResponse json_request_response = gson.fromJson(String.valueOf(response), JsonRequestResponse.class);
//                            JSONArray respuesta = response.getJSONArray("response");
//                            JSONObject respuestas = respuesta.getJSONObject(1);
//                            String day = respuestas.getString("flight_number");
                        for(int i =0; i<json_request_response.getResponse().size();i++){
                           String nombre = json_request_response.getResponse().get(i).getStatus();
                            Toast.makeText(BuscarVuelosActivity.this, "status:"+ nombre, Toast.LENGTH_SHORT).show();

                        }
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }

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