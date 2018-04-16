package com.imagec.kumarro.imageclassification.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imagec.kumarro.imageclassification.R;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button learnButton;
    private ImageView responsePic;
    private Map<String, String> knowledgeMap = new HashMap<String, String>();
    private String label;
    private String prob;
    private String fileString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText = findViewById(R.id.resultText);
        learnButton = findViewById(R.id.learnButton);
        responsePic = findViewById(R.id.respImgView);
        label = getIntent().getStringExtra("label");
        prob = getIntent().getStringExtra("prob");
        fileString = getIntent().getStringExtra("pic");
        Uri file = Uri.parse(fileString);
        responsePic.setImageURI(file);
        String result = "The probablity of " + label + " is " + prob;
        Toast.makeText(getApplicationContext(), label + prob, Toast.LENGTH_LONG).show();
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });
    }
}
