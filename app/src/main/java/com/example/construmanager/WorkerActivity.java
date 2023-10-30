package com.example.construmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    WorkerAdapter workerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);

        recyclerView = findViewById(R.id.rv_info_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Worker> options = new FirebaseRecyclerOptions.Builder<Worker>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Workers"), Worker.class)
                .build();

        workerAdapter = new WorkerAdapter(options);
        recyclerView.setAdapter(workerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        workerAdapter.startListening();
    }
    protected void onStop() {
        super.onStop();
        workerAdapter.startListening();
    }
}