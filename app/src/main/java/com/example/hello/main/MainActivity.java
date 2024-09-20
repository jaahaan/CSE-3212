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

    Button button, card, order, calculator;
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
        calculator = findViewById(R.id.calculator);

        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ButtonActivity.class)));
        card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BirthdayCardActivity.class)));
        order.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OrderActivity.class)));
        calculator.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), StatusBar.class)));
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

