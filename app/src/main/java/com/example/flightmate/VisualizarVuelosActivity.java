package com.example.flightmate;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class VisualizarVuelosActivity extends AppCompatActivity {

    private StorageReference storageReference;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<String> imageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_vuelos);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference("uploads/" + uid);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageUrls = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, imageUrls);
        recyclerView.setAdapter(imageAdapter);

        loadUserImages();

    }

    private void loadUserImages() {
        // Obtén la referencia al nodo donde se almacenan las imágenes del usuario en Firestore
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference userRef = storage.getReference().child("users/" + uid);

        // Obtiene las imágenes y las carga en el adaptador
        userRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                List<StorageReference> imageRefs = listResult.getItems();
                for (StorageReference imageRef : imageRefs) {
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageUrls.add(uri.toString());
                            imageAdapter.notifyDataSetChanged();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(VisualizarVuelosActivity.this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(VisualizarVuelosActivity.this, "Error al cargar las imágenes", Toast.LENGTH_SHORT).show();
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