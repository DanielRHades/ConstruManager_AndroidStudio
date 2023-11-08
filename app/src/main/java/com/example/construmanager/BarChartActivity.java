package com.example.construmanager;

import android.graphics.Color;
import android.os.Bundle;
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
    private DatabaseReference projectsRef;
    private TextView textView;
    private BarChart barChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadist);

        barChart = findViewById(R.id.barchart);
        textView = findViewById(R.id.textView1);
        projectsRef = FirebaseDatabase.getInstance().getReference("Projects");

        ArrayList<BarEntry> materials = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(materials, "Materials");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(20f);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");

        XAxis xAxis = barChart.getXAxis();
        final ArrayList<String> materialLabels = new ArrayList<>();

        projectsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {


                    for (DataSnapshot projectSnapshot : snapshot.getChildren()) {
                        DataSnapshot materialsSnapshot = projectSnapshot.child("Materials");
                        for (DataSnapshot materialSnapshot : materialsSnapshot.getChildren()) {
                            String name = materialSnapshot.child("name").getValue(String.class);
                            if (name != null) {
                                materialLabels.add(name);
                                materials.add(new BarEntry(materialLabels.size() - 1, 10 * (materialLabels.size())));
                            }
                        }
                    }

                    barData.notifyDataChanged();
                    barChart.notifyDataSetChanged();
                    barChart.invalidate();
                } else {
                    textView.setText("No hay proyectos disponibles en Firebase.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores si es necesario
                textView.setText("Error al acceder a Firebase: " + error.getMessage());
            }
        });

        xAxis.setValueFormatter(new IndexAxisValueFormatter(materialLabels));
        xAxis.setLabelCount(materialLabels.size());
        barChart.animateY(2000);
    }
}
