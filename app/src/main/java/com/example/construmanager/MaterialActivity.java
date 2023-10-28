package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MaterialActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MaterialAdapter materialAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        recyclerView = findViewById(R.id.rv_info_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Actualmente no funcionando por razones desconocidas, revisar tambien el adapter.
        FirebaseRecyclerOptions<Material> options = new FirebaseRecyclerOptions.Builder<Material>()
                .setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Projects")
                        .child("1")
                        .child("Materials"), Material.class)
                .build();
        materialAdapter = new MaterialAdapter(options);
        recyclerView.setAdapter(materialAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        materialAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        materialAdapter.stopListening();
    }
}







