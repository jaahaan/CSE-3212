package com.example.hello.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;

public class MainActivity extends AppCompatActivity {

    Button button, card, order, statusBar, calculator, form, tech, recycler, website, random;
    private static final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG,"onCreate called");

        button = findViewById(R.id.button);
        card = findViewById(R.id.card);
        order = findViewById(R.id.order);
        statusBar = findViewById(R.id.status_bar);
        calculator = findViewById(R.id.calculator);
        form = findViewById(R.id.form);
        website = findViewById(R.id.website);
        tech = findViewById(R.id.listViewActivity);
        recycler = findViewById(R.id.recyclerViewActivity);
        random = findViewById(R.id.rand);

        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ButtonActivity.class)));
        card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BirthdayCardActivity.class)));
        order.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OrderActivity.class)));
        statusBar.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), StatusBar.class)));
        calculator.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CalculatorActivity.class)));
        form.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), FormActivity.class)));
        tech.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TechActivity.class)));
        recycler.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class)));
        website.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WebViewActivity.class)));
        random.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), B.class)));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart called");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause called");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop called");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart called");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy called");
    }
}

