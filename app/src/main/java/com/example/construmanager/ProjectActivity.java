package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;
    private FirebaseAuth mAuth;
    private ImageView ivLogout;
    private FloatingActionButton fbtnAddProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mAuth = FirebaseAuth.getInstance();
        ivLogout = findViewById(R.id.iv_logout);
        recyclerView = findViewById(R.id.rv_project);
        fbtnAddProject = findViewById(R.id.fbtn_add_project);

        ivLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent myIntent = new Intent(ProjectActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        });
        fbtnAddProject.setOnClickListener(v -> {
            AddProjectActivity addProject = new AddProjectActivity();
            addProject.show(getSupportFragmentManager(),"");
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String userId = mAuth.getCurrentUser().getUid();
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        DatabaseReference projectsRef = instance.getReference().child("Projects");
        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>()
                .setQuery(projectsRef, Project.class)
                .build();

        projectAdapter = new ProjectAdapter(options);
        recyclerView.setAdapter(projectAdapter);

    }
    protected void onStart() {
        super.onStart();
        projectAdapter.startListening();
    }
    protected void onStop() {
        super.onStop();
        projectAdapter.startListening();
    }
}