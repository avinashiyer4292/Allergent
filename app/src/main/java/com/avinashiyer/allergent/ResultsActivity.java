package com.avinashiyer.allergent;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultsActivity extends AppCompatActivity {

    ProgressDialog pDialog = null;
    CircleImageView imgView;
    TextView tv;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        pDialog = new ProgressDialog(ResultsActivity.this);
        pDialog.setMessage("Analyzing OCR data...");
        pDialog.setIndeterminate(false);
        pDialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if(pDialog!=null){
                    pDialog.hide();
                    pDialog=null;
                }
            }
        }, 2000);

        imgView= (CircleImageView)findViewById(R.id.recordedImage);
        tv = (TextView)findViewById(R.id.resultText);
        lv = (ListView)findViewById(R.id.resultListView);



    }
}
