package com.example.hello.main;

import android.os.Bundle;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;

import java.util.Objects;
import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {
    private EditText nameEditText, idEditText, emailEditText, phoneEditText;
    private Spinner deptSpinner;
    private String name, id, email, phone, dept;
    private Button submit;
    private Pattern namePattern = Pattern.compile("[a-z A-Z._]+");
    LinearLayout inputLayout, outputLayout;
    TextView outputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        deptSpinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        String[] items = new String[]{"Select Department", "CSE", "EEE", "ARCH", "CE", "BuA", "ENG", "LAW", "IS", "BNG", "THM", "PH"};
        deptSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                id = idEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();

                if (name.isEmpty()){
                    nameEditText.setError("Empty!!");
                    nameEditText.requestFocus();
                }else if (!namePattern.matcher(name).matches()){
                    nameEditText.setError("Name can be only Alphabet");
                    nameEditText.requestFocus();
                } else if (id.isEmpty()){
                    idEditText.setError("Empty!!");
                    idEditText.requestFocus();
                } else if (email.isEmpty()){
                    emailEditText.setError("Empty!!");
                    emailEditText.requestFocus();
                } else if (phone.isEmpty()){
                    phoneEditText.setError("Empty!!");
                    phoneEditText.requestFocus();
                } else if (Objects.equals(dept, "Select Department")){
                    Toast.makeText(getApplicationContext(), "Please Select Department", Toast.LENGTH_SHORT).show();
                } else {
                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                    String s = "Name: " + name + "\nS Id: " + id + "\nEmail: " + email + "\nMobile Number: "+phone+ "\nDepartment: "+dept ;
                    outputText.setText(s);
                }
            }
        });

    }
}