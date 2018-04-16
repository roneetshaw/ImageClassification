package com.imagec.kumarro.imageclassification.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.imagec.kumarro.imageclassification.R;

public class ImageActivity extends AppCompatActivity {
    private ImageView snap;
    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        snap = findViewById(R.id.snap);
        String fileString = getIntent().getStringExtra("Snap");
        file = Uri.parse(fileString);
        snap.setImageURI(file);
    }
}
