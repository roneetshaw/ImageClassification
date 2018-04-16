package com.imagec.kumarro.imageclassification.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.imagec.kumarro.imageclassification.R;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button learnButton;
    private ImageView responsePic;
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

        String result = "There is a probability of " + prob + " that the image is of " + label;
        resultText.setText(result);
        final Map<String, String> urlMap = new HashMap<>();
        urlMap.put("burger", "https://en.wikipedia.org/wiki/Burger");
        urlMap.put("chickenPox", "https://en.wikipedia.org/wiki/Chickenpox");
        urlMap.put("daisy", "https://en.wikipedia.org/wiki/Bellis_perennis");
        urlMap.put("eczema", "https://en.wikipedia.org/wiki/Dermatitis");
        urlMap.put("dandelion", "https://en.wikipedia.org/wiki/Taraxacum");
        urlMap.put("pizza", "https://en.wikipedia.org/wiki/Pizza");
        urlMap.put("roses", "https://en.wikipedia.org/wiki/Rose");
        urlMap.put("sunflowers", "https://en.wikipedia.org/wiki/Helianthus");
        urlMap.put("tulips", "https://en.wikipedia.org/wiki/Tulip");


        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap.get(label)));
                startActivity(browserIntent);
            }
        });
    }

}
