package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class WorkerActivity extends AppCompatActivity {

    private ArrayList<Worker> listWorkers = new ArrayList<>();
    private RecyclerView rvInfoProject;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        loadFakeDataCategories();
        associateViewXML();

        WorkerAdapter myAdapter = new WorkerAdapter(listWorkers);

        rvInfoProject.setAdapter(myAdapter);
        rvInfoProject.setLayoutManager(new LinearLayoutManager(this));
    }

    private void associateViewXML() {
        rvInfoProject = findViewById(R.id.rv_info_project);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(WorkerActivity.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        });
    }

    private void loadFakeDataCategories() {
        listWorkers.add(new Worker("Worker 1", "Occupation 1", "Rank 1"));
        listWorkers.add(new Worker("Worker 2", "Occupation 2", "Rank 2"));
        listWorkers.add(new Worker("Worker 3", "Occupation 3", "Rank 3"));
        listWorkers.add(new Worker("Worker 4", "Occupation 4", "Rank 4"));
    }
}