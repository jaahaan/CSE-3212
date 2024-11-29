package com.example.hello.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.hello.A;
import com.example.hello.B;
import com.example.hello.C;
import com.example.hello.E;
import com.example.hello.MainActivity;
import com.example.hello.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

        setContentView(R.layout.activity_splash_screen);
        auth = FirebaseAuth.getInstance();
        new Handler().postDelayed(() -> {
            if(auth.getCurrentUser()!= null && auth.getCurrentUser().isEmailVerified()){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else{
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
            finish();
        }, 1500);
    }
}