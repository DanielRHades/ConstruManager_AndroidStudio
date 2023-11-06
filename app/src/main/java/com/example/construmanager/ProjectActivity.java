package com.example.construmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProjectActivity extends AppCompatActivity {
    private EditText editText;
    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;
    private FirebaseAuth mAuth;
    private ImageView ivLogout;
    private FloatingActionButton fbtnAddProject;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        ivLogout = findViewById(R.id.iv_logout);
        recyclerView = findViewById(R.id.rv_project);
        fbtnAddProject = findViewById(R.id.fbtn_add_project);
        editText = findViewById(R.id.et_search);

        ivLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent myIntent = new Intent(ProjectActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
        });

        fbtnAddProject.setOnClickListener(v -> {
            AddProjectActivity addProject = new AddProjectActivity(projectAdapter);
            addProject.show(getSupportFragmentManager(),"");
       });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Workers")
                        .child(userId)
                        .child("Projects"), Project.class)
                .build();

        projectAdapter = new ProjectAdapter(options);
        projectAdapter.startListening();
        recyclerView.setAdapter(projectAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null)
                {
                    preSearch(s.toString());
                }
                else
                {
                    preSearch("");
                }
            }
        });
    }
    protected void onStart() {
        super.onStart();
        projectAdapter.startListening();
    }
    protected void onStop() {
        super.onStop();
        projectAdapter.startListening();
    }
    private void preSearch(String s)
    {
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>()
                .setQuery(instance.getReference().child("Projects").orderByChild("name").startAt(s).endAt(s + "\uf8ff"), Project.class)
                .build();

        projectAdapter = new ProjectAdapter(options);
        projectAdapter.startListening();
        recyclerView.setAdapter(projectAdapter);

    }
}