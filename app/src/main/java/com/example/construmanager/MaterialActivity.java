package com.example.construmanager;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MaterialActivity extends AppCompatActivity {
    private String projectId;
    private ImageView ivBack;
    private Button  btnGoMaterials, btnGoWorkers,btnGoStatistics;
    private RecyclerView recyclerView;
    private MaterialAdapter materialAdapter;
    private FloatingActionButton fbtnAddMaterial;
    private BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        projectId = getIntent().getStringExtra("id");
        barChart = findViewById(R.id.barchart);
        barChart.setVisibility(View.GONE);

        btnGoMaterials = findViewById(R.id.btn_go_materials);
        btnGoWorkers = findViewById(R.id.btn_go_workers);
        btnGoStatistics = findViewById(R.id.btn_go_statistics);
        btnGoMaterials.setBackgroundColor(getResources().getColor(R.color.black));
        btnGoWorkers.setBackgroundColor(getResources().getColor(R.color.disabled_grey));
        btnGoStatistics.setBackgroundColor(getResources().getColor(R.color.disabled_grey));
        fbtnAddMaterial = findViewById(R.id.fbtn_add);


        btnGoStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(MaterialActivity.this, BarChartActivity.class);
            intent.putExtra("id", projectId);
            startActivity(intent);
            finish();
        });

        btnGoWorkers.setOnClickListener(v -> {
            Intent intent = new Intent(MaterialActivity.this, WorkerActivity.class);
            intent.putExtra("id", projectId);
            startActivity(intent);
            finish();
        });


        fbtnAddMaterial.setOnClickListener(v -> {
            AddMaterialActivity addMaterial = new AddMaterialActivity(projectId);
            addMaterial.show(getSupportFragmentManager(),"");
        });

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(MaterialActivity.this, ProjectActivity.class);
            startActivity(myIntent);
            finish();
        });

        recyclerView = findViewById(R.id.rv_info_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Material> options = new FirebaseRecyclerOptions.Builder<Material>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Projects")
                        .child(projectId)
                        .child("Materials"), Material.class)
                .build();
        materialAdapter = new MaterialAdapter(options);
        materialAdapter.startListening();
        recyclerView.setAdapter(materialAdapter);
    }
}







