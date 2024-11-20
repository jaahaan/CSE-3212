package com.example.hello.main;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.main.adapter.RecyclerViewAdapter;
import com.example.hello.main.model.Model;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

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
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        ArrayList<Model> data = new ArrayList<>();

        for (int i = 0; i<title.length; i++){
            Model obj = new Model();
            obj.setTitle(title[i]);
            obj.setSubtitle(subtitle[i]);
            obj.setImageId(imgid[i]);
            data.add(obj);
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data, getApplicationContext());

        recyclerView.setAdapter(adapter);
    }
}

