package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView tvEmailDisplay;
    private Button btnLogout,btnGoMaterials,btnGoProject;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmailDisplay=findViewById(R.id.tv_email_display);
        btnLogout=findViewById(R.id.btn_logout);
        btnGoMaterials=findViewById(R.id.btn_go_materials);
        btnGoProject=findViewById(R.id.btn_go_project);
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if ( user == null){
            // Si no se ha iniciado sesión manda al usuario al login
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        }else{
            // De lo contrario agarra el email y lo imprime
            tvEmailDisplay.setText(user.getEmail());
        }
        btnLogout.setOnClickListener(v -> {
            // Cierra sesión y regresa el usuario al login
            mAuth.signOut();
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        });
        btnGoMaterials.setOnClickListener(v -> {
            // Manda al usuario a la vista de materials
            Intent myIntent = new Intent(MainActivity.this, MaterialActivity.class);
            startActivity(myIntent);
            finish();
        });
        btnGoProject.setOnClickListener(v -> {
            // Manda al usuario a la vista de projects
            Intent myIntent = new Intent(MainActivity.this, ProjectActivity.class);
            startActivity(myIntent);
            finish();
        });
    }
}