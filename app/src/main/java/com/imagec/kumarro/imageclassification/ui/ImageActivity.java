package com.imagec.kumarro.imageclassification.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.imagec.kumarro.imageclassification.R;
import com.imagec.kumarro.imageclassification.util.MultipartRequest;

public class ImageActivity extends AppCompatActivity {
    private ImageView snap;
    public Uri file;
    private String fileString;
    private ProgressBar progressBar;
    private String response = "Network Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        snap = findViewById(R.id.snap);
        fileString = getIntent().getStringExtra("Snap");
        snap.setImageURI(Uri.parse(fileString));
        Log.i("fileURI", fileString.toString());

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
            if(s!=null){
            progressBar.setVisibility(View.GONE);

            String label = s.substring(0, s.indexOf(':'));
            String prob = s.substring(s.indexOf(':') + 1);
            Log.e("ROXXX0", label);
            Log.e("ROXXX0", prob);
            Intent intent = new Intent(ImageActivity.this, ResultActivity.class);
            intent.putExtra("label", label);
            intent.putExtra("prob", prob);
            intent.putExtra("pic", fileString);
            startActivity(intent);}
            else{
                Toast.makeText(ImageActivity.this, "Failed to connect to Internet.", Toast.LENGTH_SHORT).show();
            }
        }

        private String uploadFile() {
            String filePath = fileString;
            Log.e("filePath:", filePath);
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            Log.e("fileName:", fileName);
            MultipartRequest multipartRequest;
            multipartRequest = new MultipartRequest(getApplicationContext());
            multipartRequest.addFile("photo", filePath, fileName);
            String respon = multipartRequest.execute("http://104.211.240.116:5000/upload");
            return respon;
        }
    }
}
