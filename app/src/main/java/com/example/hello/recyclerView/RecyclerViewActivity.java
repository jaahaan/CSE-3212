package com.example.hello.recyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.tech.AddTechActivity;
import com.example.hello.tech.TechModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    private ArrayList<Model> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        arrayList = new ArrayList<>();
        String[] title = getResources().getStringArray(R.array.tech_array);
        String[] subtitle ={
                "Sub Title 1","Sub Title 2",
                "Sub Title 3","Sub Title 4",
                "Sub Title 5", "Sub Title 6","Sub Title 7",
                "Sub Title 8","Sub Title 9",
                "Sub Title 10",
        };
        Integer[] imgid= {R.drawable.android, R.drawable.java, R.drawable.php, R.drawable.python,
                R.drawable.cpp, R.drawable.ruby, R.drawable.ajax, R.drawable.rails,
                R.drawable.net, R.drawable.perl };

        for (int i = 0; i<title.length; i++){
            Model obj = new Model(title[i], subtitle[i], imgid[i]);
            arrayList.add(obj);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new RecyclerViewAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);

    }
}

