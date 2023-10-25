package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText tfEmail, tfPassword;
    private Button btnLogin;
    private TextView tvGoRegister;
    private FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Comprueba si el usuario ya inicio sesión, de ser así lo manda a main
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tfEmail=findViewById(R.id.tf_email);
        tfPassword=findViewById(R.id.tf_password);
        btnLogin=findViewById(R.id.btn_login);
        tvGoRegister=findViewById(R.id.tv_go_register);
        mAuth=FirebaseAuth.getInstance();

        tvGoRegister.setOnClickListener(v -> {
            // Manda al usuario a la vista de regitro al interactuar con el texto resaltado
            Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(myIntent);
            finish();
        });
        btnLogin.setOnClickListener(v -> {
            String email,password;
            email= String.valueOf(tfEmail.getText());
            password= String.valueOf(tfPassword.getText());
            // Muestra mensajes de error en caso de que el correo o la contraseña estén vacíos
            if(TextUtils.isEmpty(email)){
                Toast.makeText(LoginActivity.this,"Ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this,"Ingresa una contraseña válida", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Si la información coincide con un usuario creado lo manda al main
                            Toast.makeText(LoginActivity.this, "Se inició sesión con éxito.",
                                    Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(myIntent);
                            finish();
                        } else {
                            // De lo contrario solo indica que hubo un problema
                            Toast.makeText(LoginActivity.this, "Inicio de sesión fallido.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}