package com.example.hello;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    private EditText input_num1, input_num2;
    private String num1, num2, op = "+";
    private Spinner opSpinner;
    private Button calculate, clear;
    private LinearLayout ans_field;
    private TextView ans;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        input_num1 = findViewById(R.id.num1);
        input_num2 = findViewById(R.id.num2);
        opSpinner = findViewById(R.id.op);
        calculate = findViewById(R.id.calculate);
        clear = findViewById(R.id.clear);
        ans_field = findViewById(R.id.ans_field);
        ans = findViewById(R.id.ans);

        String[] items = new String[]{"+", "-", "*", "/"};
        opSpinner.setAdapter((new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items)));
        opSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                op = opSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                op = "+";
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = input_num1.getText().toString();
                num2 = input_num2.getText().toString();
                if(num1.isEmpty()){
                    //Creating the LayoutInflater instance
                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast, findViewById(R.id.custom_toast_layout));

                    //Creating the Toast object
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();

                    input_num1.setError("Empty");
                    input_num1.requestFocus();
                } else if(num2.isEmpty()){
                    input_num2.setError("Empty");
                    input_num2.requestFocus();
                } else if(op.equals("+")){
                    float finalNum1 = Float.parseFloat(num1);
                    float finalNum2 = Float.parseFloat(num2);
                    ans_field.setVisibility(View.VISIBLE);
                    float finalAns = finalNum1 + finalNum2;
                    ans.setText(String.valueOf(finalAns));
                } else if(op.equals("-")){
                    float finalNum1 = Float.parseFloat(num1);
                    float finalNum2 = Float.parseFloat(num2);
                    ans_field.setVisibility(View.VISIBLE);
                    ans.setText(String.valueOf(finalNum1 - finalNum2));
                } else if(op.equals("*")){
                    float finalNum1 = Float.parseFloat(num1);
                    float finalNum2 = Float.parseFloat(num2);
                    ans_field.setVisibility(View.VISIBLE);
                    ans.setText(String.valueOf(finalNum1 * finalNum2));
                } else if(op.equals("/")){
                    float finalNum1 = Float.parseFloat(num1);
                    float finalNum2 = Float.parseFloat(num2);
                    ans_field.setVisibility(View.VISIBLE);
                    ans.setText(String.valueOf(finalNum1 / finalNum2));
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = "";
                num2 = "";
                input_num1.setText("");
                input_num2.setText("");
                ans.setText("");
                ans_field.setVisibility(View.GONE);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}