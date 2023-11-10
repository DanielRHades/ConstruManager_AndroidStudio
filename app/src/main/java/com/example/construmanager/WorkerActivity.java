package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerActivity extends AppCompatActivity {
    private String projectId;
    private ImageView ivBack;
    private RecyclerView recyclerView;
    private Button btnGoMaterials, btnGoWorkers,btnGoStatistics;
    private FloatingActionButton fbtnAddWorker;
    private WorkerAdapter workerAdapter;
    private BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        projectId = getIntent().getStringExtra("id");
        barChart = findViewById(R.id.barchart);
        barChart.setVisibility(View.GONE);

        fbtnAddWorker = findViewById(R.id.fbtn_add);



        fbtnAddWorker.setOnClickListener(v -> {
            AddWorkersActivity addWorker = new AddWorkersActivity(projectId,workerAdapter);
            addWorker.show(getSupportFragmentManager(),"");
        });

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(WorkerActivity.this, ProjectActivity.class);
            startActivity(myIntent);
            finish();
        });



        btnGoMaterials = findViewById(R.id.btn_go_materials);
        btnGoWorkers = findViewById(R.id.btn_go_workers);
        btnGoStatistics = findViewById(R.id.btn_go_statistics);
        btnGoWorkers.setBackgroundColor(getResources().getColor(R.color.black));
        btnGoMaterials.setBackgroundColor(getResources().getColor(R.color.disabled_grey));
        btnGoStatistics.setBackgroundColor(getResources().getColor(R.color.disabled_grey));

        btnGoMaterials.setOnClickListener(v -> {
            Intent intent = new Intent(WorkerActivity.this, MaterialActivity.class);
            intent.putExtra("id", projectId);
            startActivity(intent);
            finish();
        });
        btnGoStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(WorkerActivity.this, BarChartActivity.class);
            intent.putExtra("id", projectId);
            startActivity(intent);
            finish();
        });



        recyclerView = findViewById(R.id.rv_info_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Worker> options = new FirebaseRecyclerOptions.Builder<Worker>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Projects")
                        .child(projectId)
                        .child("Workers"), Worker.class)
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