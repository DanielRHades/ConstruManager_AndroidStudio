package com.example.construmanager;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    ArrayList<BarEntry> materials = new ArrayList<>();
    BarDataSet barDataSet;
    BarData barData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadist);
        BarChart barChart = findViewById(R.id.barchart);

        projectId = getIntent().getStringExtra("id");





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


