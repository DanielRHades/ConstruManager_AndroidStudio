package com.example.construmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class AddWorkersActivity extends DialogFragment {
    private String email, projectId, userId;
    private EditText editTxtName;
    private ImageView ivBack;
    private Button btnAccept;
    public AddWorkersActivity(String projectId) {
        this.projectId = projectId;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_workers, null);
        ivBack = dialogView.findViewById(R.id.iv_back);
        btnAccept = dialogView.findViewById(R.id.btn_accept);
        editTxtName = dialogView.findViewById(R.id.edit_txt_email_worker);

        builder.setView(dialogView);
        ivBack.setOnClickListener(v -> dismiss());
        btnAccept.setOnClickListener(v -> {
            email = String.valueOf(editTxtName.getText());
            FirebaseDatabase instance = FirebaseDatabase.getInstance();

            Task<DataSnapshot> userWithEmail = instance.getReference("Workers").orderByChild("email").equalTo(email).get();
            userWithEmail.addOnCompleteListener(task -> {
                instance.getReference("Projects")
                        .child(projectId)
                        .child("Workers")
                        .updateChildren((Map<String, Object>) task.getResult().getValue());
                dismiss();
            });

        });
        return builder.create();
    }
    // Por ahora no valida nada :3
    /*
    private boolean validateInputs(String name, String color) {

        boolean validate = true;
        //validaciones
        if (name.isEmpty()) {
            tilNameNewCategory.setError(getString(R.string.text_required));
            validate = false;
        } else {
            tilNameNewCategory.setError(null);
        }
        if (color.isEmpty() || color.equals("Color")) {
            txtMsgColorInput.setVisibility(View.VISIBLE);
            txtMsgColorInput.setText(getString(R.string.text_required));
            validate = false;
        } else {
            txtMsgColorInput.setVisibility(View.INVISIBLE);
            txtMsgColorInput.setText(getString(R.string.text_required));
        }

        return validate;
    }*/
}