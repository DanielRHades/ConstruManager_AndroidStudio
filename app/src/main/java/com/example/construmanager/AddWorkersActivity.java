package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddWorkersActivity extends DialogFragment {
    private String email;
    private EditText editTxtName;
    private ImageView ivBack;
    private Button btnAccept;
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

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = String.valueOf(editTxtName.getText());

                Worker newWorker = new Worker(email);
                FirebaseDatabase.getInstance().getReference("Projects").child(id).setValue(newProject);

                dismiss();
            }
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