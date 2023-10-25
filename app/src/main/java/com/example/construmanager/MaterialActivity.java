package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MaterialActivity extends AppCompatActivity {

    private ArrayList<Material> listMaterials = new ArrayList<>();
    private RecyclerView rvInfoProject;
    private FloatingActionButton fbtnAddMaterial;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        loadFakeDataCategories();
        associateViewXML();


        MaterialAdapter myAdapter = new MaterialAdapter(listMaterials);

        rvInfoProject.setAdapter(myAdapter);
        rvInfoProject.setLayoutManager(new LinearLayoutManager(this));
        /*fbtnAddMaterial.setOnClickListener(v -> {
            AddMaterialActivity dialog = new NewMaterialDialog();
            dialog.show(getSupportFragmentManager(), "");
        });*/
    }

    private void associateViewXML() {
        rvInfoProject = findViewById(R.id.rv_info_project);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(MaterialActivity.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        });
        fbtnAddMaterial = findViewById(R.id.fbtn_add_material);
    }

    private void loadFakeDataCategories() {
        listMaterials.add(new Material("Material 1", 10, 100.0));
        listMaterials.add(new Material("Material 2", 20, 200.0));
        listMaterials.add(new Material("Material 3", 30, 300.0));
    }
}