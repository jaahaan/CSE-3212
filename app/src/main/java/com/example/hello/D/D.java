package com.example.hello.D;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;

import java.util.ArrayList;

public class D extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_d);
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
        ArrayList<ModelD> list = new ArrayList<>();
        for (int i=0; i<title.length; i++){
            ModelD obj = new ModelD(title[i], subtitle[i], imgid[i]);
//            obj.setTitle(title[i]);
//            obj.setSubtitle(subtitle[i]);
//            obj.setImageId(imgid[i]);
            list.add(obj);
        }
        CustomAdapterD adapterD = new CustomAdapterD(list, this);
        recyclerView.setAdapter(adapterD);
    }
}