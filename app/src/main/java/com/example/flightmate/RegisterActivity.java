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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,password,nombre,apellidos,telefono;
    private FirebaseAuth auth;

    private String name,surname;

    private int phonenumber;

    private Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editTextEmailAddressRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        nombre = findViewById(R.id.editTextNombre);
        apellidos = findViewById(R.id.editTextApellidos);
        telefono = findViewById(R.id.editTextTelefono);


        registro = findViewById(R.id.buttonRegistrarse);
        auth = FirebaseAuth.getInstance();



        registro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String em = email.getText().toString();
                        String pa = password.getText().toString();
                        String te = telefono.getText().toString();
                        name = nombre.getText().toString();
                        surname = apellidos.getText().toString();

                        try {
                            phonenumber = Integer.parseInt(te);
                            System.out.println("El número convertido es: " + phonenumber);
                        } catch (NumberFormatException e) {
                            System.out.println("El String no representa un número válido.");
                        }


                        if(TextUtils.isEmpty(em)||TextUtils.isEmpty(pa))
                        {
                            Toast.makeText(RegisterActivity.this,"Error: Password and email are required.",Toast.LENGTH_SHORT).show();
                        }
                        else if(pa.length() < 6)
                        {
                            Toast.makeText(RegisterActivity.this,"Error: Password needs to be longer than 6 characters",Toast.LENGTH_SHORT).show();
                        }
                        else if(te.length() != 9)
                        {
                            Toast.makeText(RegisterActivity.this,"Error: Phone number needs to be 9 digits",Toast.LENGTH_SHORT).show();
                            telefono.setBackgroundColor(getResources().getColor(R.color.purple_500));
                        }
                        else
                        {
                            registerUser(em,pa);
                        }
                    }
                }
        );
    }


    private void registerUser(String em, String pa) {
        auth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(
                RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            // El usuario se creó exitosamente
                            FirebaseUser user = auth.getCurrentUser();
                            String userId = user.getUid();

                            // Guardar campos adicionales en Firestore
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            DocumentReference userRef = db.collection("user").document(userId);

                            // Crear un objeto User con campos personalizados
                            User newUser = new User();
                            newUser.setNombre(name);
                            newUser.setApellidos(surname);
                            newUser.setTelefono(phonenumber);
                            newUser.setId(userId);
                            newUser.setEmail(em);
                            newUser.setPassword(pa);

                            // Guardar el objeto User en Firestore
                            userRef.set(newUser).addOnSuccessListener(aVoid -> {
                                        Toast.makeText(RegisterActivity.this,"El usuario y los campos adicionales se guardaron correctamente en Firestore",Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(RegisterActivity.this,"Ocurrió un error al guardar los campos adicionales en Firestore",Toast.LENGTH_SHORT).show();
                                    });


                            Toast.makeText(RegisterActivity.this,"Successful registration",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"Sign up failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}