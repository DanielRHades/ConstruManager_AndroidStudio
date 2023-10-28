package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ProjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;
    private FirebaseAuth mAuth;
    private ImageView ivLogout;
    private Button btnGoMaterials,btnGoWorkers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mAuth = FirebaseAuth.getInstance();
        ivLogout = findViewById(R.id.iv_logout);
        btnGoMaterials = findViewById(R.id.btn_go_materials);
        btnGoWorkers = findViewById(R.id.btn_go_workers);
        recyclerView = findViewById(R.id.rv_project);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Actualmente no funcionando por razones desconocidas, revisar tambien el adapter.
        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>()
                .setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Projects"), Project.class)
                .build();

        projectAdapter = new ProjectAdapter(options);
        recyclerView.setAdapter(projectAdapter);

        ivLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent myIntent = new Intent(ProjectActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        });
        btnGoMaterials.setOnClickListener(v -> {
            Intent myIntent = new Intent(ProjectActivity.this, MaterialActivity.class);
            startActivity(myIntent);
        });
        btnGoWorkers.setOnClickListener(v -> {
            Intent myIntent = new Intent(ProjectActivity.this, WorkerActivity.class);
            startActivity(myIntent);
        });
    }
}