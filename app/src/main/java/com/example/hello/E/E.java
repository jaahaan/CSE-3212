package com.example.hello.E;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;
import com.example.hello.auth.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class E extends AppCompatActivity {


    private EditText nameEditText, emailEditText, passEditText, confirmPassEd;
    private String name, email, pass, confirmPass;
    private Button submit;
    private Pattern namePattern = Pattern.compile("[a-z A-Z._]+");
    private Pattern emailPattern = Pattern.compile("^(cse_)\\d{10}(@lus.ac.bd)$");
    private Pattern passPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    private TextView login;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_e);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        passEditText = findViewById(R.id.pass);
        confirmPassEd = findViewById(R.id.cpass);
        submit = findViewById(R.id.submit);
        login = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                email = emailEditText.getText().toString();
                pass = passEditText.getText().toString();
                confirmPass = confirmPassEd.getText().toString();
                if (!pass.equals(confirmPass)){
                    passEditText.setError("Error");
                    confirmPassEd.setError("Error");
                    passEditText.requestFocus();
                    confirmPassEd.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null && !user.isEmailVerified()){
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "Verification link is sent!!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            DocumentReference reference = firestore.collection("Users").document(user.getUid());
                            Map<String, String> map = new HashMap<>();
                            map.put("name", name);
                            map.put("email", email);
                            map.put("uid", user.getUid());
                            reference.set(map);
                            auth.signOut();
                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                            finish();
                        }
                        else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "User already exist!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("Exception", task.getException().getMessage());
//                                    Toast.makeText(getApplicationContext(), "Exception: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                    });
                }

            }
        });
    }
}