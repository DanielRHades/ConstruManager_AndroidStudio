package com.example.construmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.UUID;

public class AddProjectActivity extends DialogFragment {
    private String projectId,name,company,type,afiliates,address;
    private EditText editTxtName, editTxtCompany, editTxtType, editTxtAfilliates, editTxtaddress;
    private Button btnAccept;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_project, null);

        btnAccept = dialogView.findViewById(R.id.btn_accept);
        editTxtName = dialogView.findViewById(R.id.edit_txt_name_proyecto);
        editTxtCompany = dialogView.findViewById(R.id.edit_txt_name_empresa);
        editTxtType = dialogView.findViewById(R.id.edit_txt_tipo);
        editTxtAfilliates = dialogView.findViewById(R.id.edit_txt_afiliados);
        editTxtaddress = dialogView.findViewById(R.id.edit_txt_direccion);

        builder.setView(dialogView);

        btnAccept.setOnClickListener(v -> {
            projectId= String.valueOf(UUID.randomUUID());
            name = String.valueOf(editTxtName.getText());
            company = String.valueOf(editTxtCompany.getText());
            type = String.valueOf(editTxtType.getText());
            afiliates = String.valueOf(editTxtAfilliates.getText());
            address = String.valueOf(editTxtaddress.getText());

            Project newProject = new Project(projectId,name,company,address,afiliates);
            FirebaseDatabase.getInstance().getReference("Projects").child(projectId).setValue(newProject);

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseDatabase instance = FirebaseDatabase.getInstance();

            Task<DataSnapshot> userWithEmail = instance.getReference("Workers").child(userId).get();
            userWithEmail.addOnCompleteListener(task -> {
                instance.getReference("Projects")
                        .child(projectId)
                        .child("Workers")
                        .child(userId)
                        .updateChildren((Map<String, Object>) task.getResult().getValue());
                dismiss();
        });
    });
    return builder.create();
}}