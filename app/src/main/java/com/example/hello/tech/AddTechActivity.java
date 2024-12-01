package com.example.hello.tech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.hello.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AddTechActivity extends AppCompatActivity {
    private ImageView imageView;
    private Uri imagePath;
    private static final int IMAGE_REQ = 1;
    private EditText titleEditText, subtitleEditText;
    private String  title, subtitle;
    private Button button;
    private DatabaseReference reference;
    private ProgressBar progressBar;
    private static final  String tag = "image";

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_tech);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView = findViewById(R.id.imageView);
        titleEditText = findViewById(R.id.title);
        subtitleEditText = findViewById(R.id.subtitle);
        button = findViewById(R.id.add);
        progressBar = findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Tech Items");

        imageView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(AddTechActivity.this, Manifest.permission.READ_MEDIA_IMAGES)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMAGE_REQ);
            } else {
                ActivityCompat.requestPermissions(AddTechActivity.this, new String[] {
                        Manifest.permission.READ_MEDIA_IMAGES
                }, IMAGE_REQ);
            }
        });

        button.setOnClickListener(v -> {
            title = titleEditText.getText().toString();
            subtitle = subtitleEditText.getText().toString();
            if (title.isEmpty()){
                titleEditText.setError("Empty!!");
                titleEditText.requestFocus();
            } else if (subtitle.isEmpty()){
                subtitleEditText.setError("Empty!!");
                subtitleEditText.requestFocus();
            } else if (imagePath == null) {
                Toast.makeText(this, "Please select an image!!", Toast.LENGTH_SHORT).show();
            } else{
                progressBar.setVisibility(View.VISIBLE);
                uploadImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQ && resultCode == RESULT_OK && data != null){
            imagePath = data.getData();
            Picasso.get().load(imagePath).into(imageView);
        }
    }

    private void uploadImage() {
        MediaManager.get().upload(imagePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                Log.d(tag, "onSuccess");
                String imageUrl = (String) resultData.get("secure_url");
                uploadData(imageUrl);
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {

            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
            }
        }).dispatch();
    }

    private void uploadData(String imageUrl) {
        String key = reference.push().getKey();
        Log.d(tag, "imageUrl " + imageUrl);
        TechModel data = new TechModel(title, subtitle, imageUrl, key);
        assert key != null;
        progressBar.setVisibility(View.GONE);

        reference.child(key).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                titleEditText.setText("");
                subtitleEditText.setText("");
                imagePath = null;
                imageView.setImageResource(R.drawable.android);
                Toast.makeText(getApplicationContext(), "Added Successfully!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }




}