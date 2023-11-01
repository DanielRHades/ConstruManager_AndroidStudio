package com.example.construmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class EditMaterialActivity extends DialogFragment {
    private String projectId;
    private EditText editTextAvailable,editTextMissing, editTextPayed,editTextOwed;
    private Button btnEdit;
    public EditMaterialActivity(String projectId) {
        this.projectId = projectId;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_edit_material, null);

        editTextAvailable = dialogView.findViewById(R.id.etxt_available_material);
        editTextMissing = dialogView.findViewById(R.id.etxt_missing_material);
        editTextPayed = dialogView.findViewById(R.id.etxt_payed_material);
        editTextOwed = dialogView.findViewById(R.id.etxt_owed_material);
        btnEdit = dialogView.findViewById(R.id.btn_edit_material);

        builder.setView(dialogView);
        btnEdit.setOnClickListener(v -> {
            String available = editTextAvailable.getText().toString();
            String missing = editTextMissing.getText().toString();
            String payed = editTextPayed.getText().toString();
            String owed = editTextOwed.getText().toString();
            FirebaseDatabase instance = FirebaseDatabase.getInstance();

            Material newMaterial = new Material(available, missing, payed, owed);
            instance.getReference("Projects")
                    .child(projectId)
                    .child("Materials")
                    .orderByChild("projectId")
                    .equalTo(projectId)
                    .getRef().updateChildren((Map<String, Object>) newMaterial);
            dismiss();
        });

        return builder.create();
    }
}