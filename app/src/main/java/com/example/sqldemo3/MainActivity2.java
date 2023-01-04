package com.example.sqldemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    //Creating the objects of textview and button
    TextView tv_webSiteVal;
    Button btn_webSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Linking this up with the activitymain2 xml
        tv_webSiteVal = findViewById(R.id.tv_webValue);

        //Making textview scrollable as there is a lot of text
        tv_webSiteVal.setMovementMethod(new ScrollingMovementMethod());;
        btn_webSite = findViewById(R.id.btn_getWeb);

        //when button is clicked then call the doIt method
        btn_webSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new doIT().execute();
            }
        });

    }

    public class doIT extends AsyncTask<Void,Void,Void> {
        String words;

        @Override
        protected Void doInBackground(Void... params) {

            //I used jsoup for this  jsoup is ........


            //Using jsoup libray to help with the coding -- into gradle dependency
            org.jsoup.nodes.Document document = null;
            try {
                //calling the url, and having access to the internet through the use of jsoup
                //have added permission into manifest file to allow app to use the internet
                document = Jsoup.connect("https://www.vans.co.uk/shop/en-gb/vans-gb/women-shoes").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //using the jsoup to get product list that is returned from the url above
            org.jsoup.select.Elements elements = document.getElementsByClass("product-list product-list-js product-list-position-relative");
            //making it the text and adding that text to the textview box so we can see it
            words = elements.text();
            tv_webSiteVal.setText(words);
            return null;
        }
    }
}