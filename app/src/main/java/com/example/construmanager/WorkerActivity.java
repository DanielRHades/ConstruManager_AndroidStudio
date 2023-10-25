package com.example.construmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerActivity extends AppCompatActivity {

    private ArrayList<Worker> listWorkers;

    private DatabaseReference dataBase;
    private RecyclerView rvInfoProject;
    private WorkerAdapter myAdapter;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);

        rvInfoProject = findViewById(R.id.rv_info_project);
        dataBase = FirebaseDatabase.getInstance().getReference("Users");
        rvInfoProject.setHasFixedSize(true);
        rvInfoProject.setLayoutManager(new LinearLayoutManager(this));

        listWorkers = new ArrayList<>();

        myAdapter = new WorkerAdapter(this, listWorkers);

        rvInfoProject.setAdapter(myAdapter);

        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Worker worker = dataSnapshot.getValue(Worker.class);
                    listWorkers.add(worker);
                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}