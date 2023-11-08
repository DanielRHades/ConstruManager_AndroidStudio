package com.example.construmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class EditMaterialActivity extends AppCompatActivity {
    private ImageView iv_back;
    private EditText editTextAvailable,editTextMissing, editTextPayed,editTextOwed;
    private Button btnEdit;
    private String projectId, materialId, name,available,missing,payed,owed;
    private int price;
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_material);
        projectId = getIntent().getStringExtra("projectId");
        materialId = getIntent().getStringExtra("materialId");
        name = getIntent().getStringExtra("name");
        price = getIntent().getIntExtra("price",0);
        available = getIntent().getStringExtra("available");
        missing = getIntent().getStringExtra("missing");
        payed = getIntent().getStringExtra("payed");
        owed = getIntent().getStringExtra("owed");

        editTextAvailable = findViewById(R.id.etxt_available_material);
        editTextMissing = findViewById(R.id.etxt_missing_material);
        editTextPayed = findViewById(R.id.etxt_payed_material);
        editTextOwed = findViewById(R.id.etxt_owed_material);
        btnEdit = findViewById(R.id.btn_edit_material);
        iv_back = findViewById(R.id.iv_back);

        editTextAvailable.setText(available);
        editTextMissing.setText(missing);
        editTextPayed.setText(payed);
        editTextOwed.setText(owed);

        iv_back.setOnClickListener(view -> {
            Intent myIntent = new Intent(EditMaterialActivity.this, MaterialActivity.class);
            myIntent.putExtra("id",projectId);
            startActivity(myIntent);
            finish();
        });

        btnEdit.setOnClickListener(v -> {
            String available = editTextAvailable.getText().toString();
            String missing = editTextMissing.getText().toString();
            String payed = editTextPayed.getText().toString();
            String owed = editTextOwed.getText().toString();
            FirebaseDatabase instance = FirebaseDatabase.getInstance();

            Material newMaterial = new Material(projectId, materialId, name, price, available, missing, payed, owed);
            instance.getReference("Projects")
                    .child(projectId)
                    .child("Materials")
                    .child(materialId)
                    .setValue(newMaterial);

            Intent myIntent = new Intent(EditMaterialActivity.this, MaterialActivity.class);
            myIntent.putExtra("id",projectId);
            startActivity(myIntent);
            finish();
        });
    }
}