package com.example.flightmate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvgmail = findViewById(R.id.textViewGmail);
        TextView tvnombre = findViewById(R.id.textViewNombre);
        TextView tvapellidos = findViewById(R.id.textViewApellidos);
        TextView tvtel = findViewById(R.id.textViewNumeroTelefono);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        DocumentReference userRef = db.collection("user").document(userId);

        userRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // El documento existe, accede a los datos
                            String email = document.getString("email");
                            String nombre = document.getString("nombre");
                            String apellidos = document.getString("apellidos");
                            int telefono = document.getLong("telefono").intValue();
                            // Realiza acciones con los datos obtenidos
                            tvgmail.setText("Gmail: " + email);
                            tvnombre.setText("Nombre: " + nombre);
                            tvapellidos.setText("Apellidos: " + apellidos);
                            tvtel.setText("Número de teléfono: " + telefono);


                        } else {
                            Toast.makeText(PerfilActivity.this,"Error:el documento no existe",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PerfilActivity.this,"Error:No se pudo obtener los datos",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}