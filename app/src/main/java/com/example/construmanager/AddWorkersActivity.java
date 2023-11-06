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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Map;

public class AddWorkersActivity extends DialogFragment {
    private String email, projectId;
    private EditText editTxtName;
    private Button btnAccept;
    private WorkerAdapter workerAdapter;
    public AddWorkersActivity(String projectId, WorkerAdapter workerAdapter) {
        this.projectId = projectId;
        this.workerAdapter = workerAdapter;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_workers, null);
        btnAccept = dialogView.findViewById(R.id.btn_accept);
        editTxtName = dialogView.findViewById(R.id.edit_txt_email_worker);

        builder.setView(dialogView);
        btnAccept.setOnClickListener(v -> {
            email = String.valueOf(editTxtName.getText());
            FirebaseDatabase instance = FirebaseDatabase.getInstance();

            Query workerQuery = instance.getReference("Workers").orderByChild("email").equalTo(email);
            Task<DataSnapshot> userWithEmail = instance.getReference("Workers").orderByChild("email").equalTo(email).get();
            userWithEmail.addOnCompleteListener(task -> {
                instance.getReference("Projects")
                        .child(projectId)
                        .child("Workers")
                        .updateChildren((Map<String, Object>) task.getResult().getValue());
            });
            DatabaseReference projectData = instance.getReference("Projects").child(projectId);
            projectData.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    workerQuery.getRef()
                            .child("Projects")
                            .child(projectId).setValue(task.getResult().getValue()).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    dismiss();
                                }
                            });
                }

            });
            dismiss();
        });
        return builder.create();
    }
}