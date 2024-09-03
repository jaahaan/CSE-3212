package com.example.hello;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

import java.util.Objects;

public class Converter extends AppCompatActivity {
    private Spinner fromSpinner, toSpinner;
    private EditText input_field;
    private Button convert, clear;
    private String num, res, spinner1 = "Select", spinner2 = "Select";
    private LinearLayout ans_field;
    private TextView ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title

        setContentView(R.layout.activity_converter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fromSpinner = findViewById(R.id.from_sp);
        toSpinner = findViewById(R.id.to_sp);
        input_field = findViewById(R.id.input_field);
        convert = findViewById(R.id.convert);
        clear = findViewById(R.id.clear);
        ans_field = findViewById(R.id.ans_field);
        ans = findViewById(R.id.ans);

        String[] items = new String[]{"Select", "Decimal", "Binary", "Octal", "Hexadecimal"};
        fromSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        toSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1 = fromSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner1 = "Select";
            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2 = toSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner2 = "Select";
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = input_field.getText().toString();
                if (Objects.equals(spinner1, "Select")) {
                    Toast.makeText(getApplicationContext(), "Please Select from which number system you want to convert.", Toast.LENGTH_SHORT).show();
                    fromSpinner.requestFocus();
                } else if (Objects.equals(spinner2, "Select")) {
                    Toast.makeText(getApplicationContext(), "Please Select to which number system you want to convert.", Toast.LENGTH_SHORT).show();
                    toSpinner.requestFocus();
                } else if (num.isEmpty()) {
                    input_field.setError("Empty!!!");
                    input_field.requestFocus();
                } else if (Objects.equals(spinner1, "Decimal")) {
                    if (Objects.equals(spinner2, "Binary")) {
                        res = Integer.toBinaryString(Integer.parseInt(num));
                        ans_field.setVisibility(View.VISIBLE);
                        ans.setText(res);
                    } else if (Objects.equals(spinner2, "Octal")) {
                        res = Integer.toOctalString(Integer.parseInt(num));
                        ans_field.setVisibility(View.VISIBLE);
                        ans.setText(res);
                    } else if (Objects.equals(spinner2, "Hexadecimal")) {
                        res = Integer.toHexString(Integer.parseInt(num));
                        ans_field.setVisibility(View.VISIBLE);
                        ans.setText(res);
                    }
                } else if (Objects.equals(spinner1, "Binary")) {
                    if (Objects.equals(spinner2, "Decimal")) {
                         res = String.valueOf(Integer.parseInt(num,2));
                         ans_field.setVisibility(View.VISIBLE);
                         ans.setText(res);
//                        int decimal = Integer.parseInt(num,2);
//                        ans.setText(decimal + "");
                    } else if (Objects.equals(spinner2, "Octal")) {

                    } else if (Objects.equals(spinner2, "Hexadecimal")) {

                    }
                } else if (Objects.equals(spinner1, "Octal")) {
                    if (Objects.equals(spinner2, "Decimal")) {

                    } else if (Objects.equals(spinner2, "Binary")) {

                    } else if (Objects.equals(spinner2, "Hexadecimal")) {

                    }
                } else if (Objects.equals(spinner1, "Hexadecimal")) {
                    if (Objects.equals(spinner2, "Decimal")) {

                    } else if (Objects.equals(spinner2, "Binary")) {

                    } else if (Objects.equals(spinner2, "Octal")) {

                    }
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans_field.setVisibility(View.GONE);
                num = "";
                res = "";
                input_field.setText("");
            }
        });
    }
}