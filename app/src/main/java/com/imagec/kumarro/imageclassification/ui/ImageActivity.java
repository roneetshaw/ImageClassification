package com.imagec.kumarro.imageclassification.ui;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.imagec.kumarro.imageclassification.R;
import com.imagec.kumarro.imageclassification.util.MultipartRequest;

public class ImageActivity extends AppCompatActivity {
    private ImageView snap;
    private Uri file;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        snap = findViewById(R.id.snap);
        String fileString = getIntent().getStringExtra("Snap");
        file = Uri.parse(fileString);
        snap.setImageURI(file);

    }

    public void uploadSnap(View view) {
        new UploadFileToServer().execute();
    }

    /**
     * Uploading the file to server
     */
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }

        private String uploadFile() {
            String filePath = file.getPath();
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            MultipartRequest multipartRequest;

            multipartRequest = new MultipartRequest(getApplicationContext());
            multipartRequest.addFile("photo", filePath, fileName);
            multipartRequest.execute("http://192.168.0.102:5000/upload");
            return "Success";
        }
    }
}
