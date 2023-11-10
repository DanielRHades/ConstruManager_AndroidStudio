package com.example.construmanager;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BarChartActivity extends AppCompatActivity {
    private DatabaseReference materialRef;

    private String projectId;
    private RecyclerView rv_other;

    ArrayList<BarEntry> materials = new ArrayList<>();
    BarDataSet barDataSet;
    Button btnGoMaterials,btnGoWorkers,btnGoStatistics;
    BarData barData;
    ImageView ivBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        BarChart barChart = findViewById(R.id.barchart);
        RecyclerView rv_other = findViewById(R.id.rv_info_project);


        projectId = getIntent().getStringExtra("id");

        barChart.setVisibility(View.VISIBLE);

        rv_other.setVisibility(View.GONE);

        btnGoMaterials = findViewById(R.id.btn_go_materials);
        btnGoWorkers = findViewById(R.id.btn_go_workers);
        btnGoStatistics = findViewById(R.id.btn_go_statistics);
        btnGoMaterials.setBackgroundColor(getResources().getColor(R.color.disabled_grey));
        btnGoWorkers.setBackgroundColor(getResources().getColor(R.color.disabled_grey));
        btnGoStatistics.setBackgroundColor(getResources().getColor(R.color.black));
        btnGoMaterials.setOnClickListener(v -> {

            Intent intent = new Intent(BarChartActivity.this, MaterialActivity.class);
            intent.putExtra("id", projectId);
            startActivity(intent);
            finish();
        });

        btnGoWorkers.setOnClickListener(v -> {
            Intent intent = new Intent(BarChartActivity.this, WorkerActivity.class);
            intent.putExtra("id", projectId);
            startActivity(intent);
            finish();
        });


        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(BarChartActivity.this, ProjectActivity.class);
            startActivity(myIntent);
            finish();
        });



        materialRef = FirebaseDatabase.getInstance().getReference("Projects")
                .child(projectId).child("Materials");
        materialRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<Material> lista = new ArrayList<>();
                    String sap = "";
                    String name = "";
                    for (DataSnapshot projectSnapshot : snapshot.getChildren()) {
                        sap = projectSnapshot.getKey();
                        Material miMaterial = projectSnapshot.getValue(Material.class);
                        lista.add(miMaterial);
                    }
                    Integer cotador = 0;
                    String[] miy = new String[lista.size()];
                    for (Material miMateri : lista) {
                        materials.add(new BarEntry(cotador, miMateri.getPrice()));
                        miy[cotador] = miMateri.getName();
                        cotador+=1;
                    }

                    barDataSet = new BarDataSet(materials, "Materials");

                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(20f);

                    barData = new BarData((barDataSet));

                    barChart.setFitBars(true);
                    barChart.setData(barData);
                    barChart.getDescription().setText("Bar Chart Example");

                    XAxis xAxis = barChart.getXAxis();

                    xAxis.setValueFormatter(new IndexAxisValueFormatter(miy));
                    xAxis.setLabelCount(miy.length);
                    barChart.animateY(2000);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}


