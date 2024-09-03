package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private EditText name, email, password;
    private TextView sighInRedirect;
    private Button signUp;
    private FirebaseAuth mAuth;
    private Pattern namePattern = Pattern.compile("[a-z A-Z.]+");
    private Pattern emailPattern = Pattern.compile("(cse_)[\\d]{10}(@lus.ac.bd)");
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signUp);
        sighInRedirect = findViewById(R.id.sighInRedirect);
        progressBar = findViewById(R.id.progressBar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String signUpName = name.getText().toString();
                String signUpEmail = email.getText().toString();
                String signUpPassword = password.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                if (name.getText().toString().isEmpty()) {
                    name.setError("Empty");
                    name.requestFocus();
                    return;
                } else if (!namePattern.matcher(name.getText().toString()).matches()) {
                    name.setError("Name can be only Alphabet");
                    name.requestFocus();
                    return;
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Empty");
                    email.requestFocus();
                    return;
                } else if (!emailPattern.matcher(email.getText().toString()).matches()) {
                    email.setError("This is not a Student Email");
                    email.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(signUpEmail, signUpPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Authentication Successfull.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        sighInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });
    }
}