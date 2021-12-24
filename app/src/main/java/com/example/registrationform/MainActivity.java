package com.example.registrationform;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addBtn;

    databasehelper db;
    ArrayList<String> id, nama, no_telp, alamat, gender, olahraga, waktu;
    customAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tambahData.class);
                startActivity(intent);
            }
        });
        
        db = new databasehelper(MainActivity.this);
        id = new ArrayList<>();
        nama = new ArrayList<>();
        no_telp = new ArrayList<>();
        alamat = new ArrayList<>();
        gender = new ArrayList<>();
        olahraga = new ArrayList<>();
        waktu = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new customAdapter(MainActivity.this, this, id, nama, no_telp, alamat, gender, olahraga, waktu);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = db.readData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                nama.add(cursor.getString(1));
                no_telp.add(cursor.getString(2));
                alamat.add(cursor.getString(3));
                gender.add(cursor.getString(4));
                olahraga.add(cursor.getString(5));
                waktu.add(cursor.getString(6));
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Display Start",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Display is running",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, " Display temporarily stopped",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Display stopped",Toast.LENGTH_SHORT).show();
    }
}