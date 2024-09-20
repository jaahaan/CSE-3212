package com.example.hello.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;

public class ButtonActivity extends AppCompatActivity {
    Button red, green;
    TextView text;
    String Tag = "ButtonActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        this.getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

        setContentView(R.layout.activity_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d(Tag,"onCreate invoked");

        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        text = findViewById(R.id.text);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Red button is clicked!!");
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Green button is clicked!!");
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");

                LayoutInflater li = getLayoutInflater();   //Creating the LayoutInflater instance
                //Getting the View object as defined in the .xml file
                View layout = li.inflate(R.layout.custom_toast, findViewById(R.id.toast));
                Toast toast = new Toast(getApplicationContext());   //Creating the Toast object
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setView(layout);//setting the view of custom toast layout
                toast.show();

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag,"onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag,"onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag,"onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag,"onDestroy invoked");
    }


}