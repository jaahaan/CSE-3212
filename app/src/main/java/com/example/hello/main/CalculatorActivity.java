package com.example.hello.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;

public class CalculatorActivity extends AppCompatActivity {
    EditText input_num1, input_num2;
    String num1, num2, op;
    TextView ans;
    Button button;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input_num1 = findViewById(R.id.num1);
        input_num2 = findViewById(R.id.num2);
        ans = findViewById(R.id.ans);
        button = findViewById(R.id.sum);
        spinner = findViewById(R.id.spinner);

        String[] items = new String[]{"+", "-", "*", "/"};

        button.setOnClickListener(v -> {
            num1 = input_num1.getText().toString();
            num2 = input_num2.getText().toString();
            if (num1.isEmpty()){
                input_num1.setError("Empty");
                input_num1.requestFocus();
            } else if (num2.isEmpty()){
                input_num2.setError("Empty");
                input_num2.requestFocus();
            } else {
                float finalNum1 = Float.parseFloat(num1);
                float finalNum2 = Float.parseFloat(num2);
                float finalAns = finalNum1 + finalNum2;
                ans.setText(String.valueOf(finalAns));
            }
        });
    }
}