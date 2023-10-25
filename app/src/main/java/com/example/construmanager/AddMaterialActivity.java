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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class AddMaterialActivity extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_material, null);

        builder.setView(dialogView).setMessage("Nuevo Material");
// Handle the cancel button

        /*btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editTitleName.getText().toString().trim();
                color = btnColor.getText().toString();

                if (validateInputs(name, color)) {
                    Category newCategory = new Category(name, color);
                    CategoryActivity categoryActivity = new CategoryActivity();
                    categoryActivity.saveCategory(newCategory);
                    dismiss();
                }
            }
        });*/
        return builder.create();
    }

    /*private boolean validateInputs(String name, String color) {

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