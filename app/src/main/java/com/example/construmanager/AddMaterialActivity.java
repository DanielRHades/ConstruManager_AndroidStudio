package com.example.construmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

public class AddMaterialActivity extends DialogFragment {
    private String projectId;
    private EditText editTextName, editTextPrice;
    private Button btnAdd;
    public AddMaterialActivity(String projectId) {
        this.projectId = projectId;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_material, null);

        editTextName = dialogView.findViewById(R.id.etxt_name_material);
        editTextPrice = dialogView.findViewById(R.id.etxt_price_material);
        btnAdd = dialogView.findViewById(R.id.btn_add_material);

        btnAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String price = editTextPrice.getText().toString();
            FirebaseDatabase instance = FirebaseDatabase.getInstance();

            Material newMaterial = new Material(projectId, name, price);
            instance.getReference("Projects")
                    .child(projectId)
                    .child("Materials")
                    .child(name).setValue(newMaterial);
            dismiss();
        });

        builder.setView(dialogView);
        return builder.create();
    }
}