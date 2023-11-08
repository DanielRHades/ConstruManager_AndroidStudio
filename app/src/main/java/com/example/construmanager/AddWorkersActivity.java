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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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


            Task<DataSnapshot> userWithEmail = instance.getReference("Workers").orderByChild("email").equalTo(email).get();
            userWithEmail.addOnCompleteListener(task -> {
                instance.getReference("Projects")
                        .child(projectId)
                        .child("Workers")
                        .updateChildren((Map<String, Object>) task.getResult().getValue());
            });

            Query workerQuery = instance.getReference("Workers").orderByChild("email").equalTo(email);

            workerQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot workerSnapshot : dataSnapshot.getChildren()) {
                            String workerId = workerSnapshot.getKey();
                            DatabaseReference workerProjectsRef = instance.getReference("Workers")
                                    .child(workerId)
                                    .child("Projects");
                            DatabaseReference projectToCopyRef = instance.getReference("Projects")
                                    .child(projectId);
                            projectToCopyRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot projectSnapshot) {
                                    if (projectSnapshot.exists()) {
                                        workerProjectsRef.child(projectId).setValue(projectSnapshot.getValue(), (databaseError, databaseReference) -> {
                                            if (databaseError == null) {
                                                dismiss();
                                            } else {
                                            }
                                        });
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }
                    } else {
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle any errors
                }
            });

            dismiss();
        });
        return builder.create();
    }
}