package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.construmanager.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText tfEmail,tfName,tfJob,tfPassword,tfConfirmPassword;
    String name, job, email;
    private Button btnLogin;
    private TextView tvGoLogin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase fireDB;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        tfEmail = findViewById(R.id.tf_email);
        tfName = findViewById(R.id.tf_name);
        tfJob = findViewById(R.id.tf_job);
        tfPassword = findViewById(R.id.tf_password);
        tfConfirmPassword = findViewById(R.id.tf_confirm_password);
        btnLogin = findViewById(R.id.btn_login);
        tvGoLogin = findViewById(R.id.tv_go_login);
        fireDB = FirebaseDatabase.getInstance();
        reference = fireDB.getReference();

        tvGoLogin.setOnClickListener(v -> {
            // Lo manda a la pantalla de inicio de sesión al interactuar con el texto resaltado
            Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        });

        btnLogin.setOnClickListener(v -> {
            String email,password,cPassword;
            email= String.valueOf(tfEmail.getText());
            password= String.valueOf(tfPassword.getText());
            cPassword= String.valueOf(tfConfirmPassword.getText());

            // Muestra mensajes de error en caso de que el correo o la contraseña estén vacíos o las contraseñas no coincidan
            if(TextUtils.isEmpty(email)){
                Toast.makeText(RegisterActivity.this,"Ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(RegisterActivity.this,"Ingresa una contraseña válida", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!cPassword.equals(password)){
                Toast.makeText(RegisterActivity.this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }
            // Método de firebase que crea un usuario solo con el correo y una contraseña
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            name = String.valueOf(tfName.getText());
                            job = String.valueOf(tfJob.getText());
                            this.email = String.valueOf(tfEmail.getText());

                            Worker new_worker = new Worker(name, job, this.email);

                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            new_worker.setId(auth.getCurrentUser().getUid());

                            reference = fireDB.getReference("Workers");

                            //reference.child(name).setValue(new_worker);
                            reference.child(new_worker.getId()).setValue(new_worker);

                            // Si la cuenta se crea correctamente manda al usuario a main
                            Toast.makeText(RegisterActivity.this, "Cuenta creada.", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(RegisterActivity.this, ProjectActivity.class);
                            startActivity(myIntent);
                            finish();

                        } else {
                            // De lo contrario solo indica que hubo un problema
                            Toast.makeText(RegisterActivity.this, "Hubo un problema.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}