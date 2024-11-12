package com.example.hello.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.R;

public class HelloActivity extends AppCompatActivity {

    private ImageView imageView;
    private ZoomControls zoomControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView = findViewById(R.id.imageView);
        zoomControls = findViewById(R.id.zoomControl);

        zoomControls.setOnZoomInClickListener(v -> {
            float x = imageView.getScaleX();
            float y = imageView.getScaleX();

            imageView.setScaleX(x+1);
            imageView.setScaleY(y+1);
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = imageView.getScaleX();
                float y = imageView.getScaleX();

                if (x>1 && y>1){
                    imageView.setScaleX(x-1);
                    imageView.setScaleY(y-1);
                }

            }
        });
    }
}