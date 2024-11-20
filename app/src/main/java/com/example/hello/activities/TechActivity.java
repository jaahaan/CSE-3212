package com.example.hello.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;
import com.example.hello.adapter.TechAdapter;

public class TechActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tech);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listView);
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


        TechAdapter adapter = new TechAdapter(this, title, subtitle, imgid);

        listView.setAdapter(adapter);
    }
}