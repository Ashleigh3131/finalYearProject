package com.example.sqldemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_webSiteVal;
    Button btn_webSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_webSiteVal = findViewById(R.id.tv_webValue);
        btn_webSite = findViewById(R.id.btn_getWeb);

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

            org.jsoup.nodes.Document document = null;
            try {

                document = Jsoup.connect("https://www.vans.co.uk/shop/en-gb/vans-gb/women-shoes").get();
                //Elements test = document.getElementsByClass("col-s-12 col-m-8 col-l-6");
                // System.out.println( "results" + test);
                //words = document.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            org.jsoup.select.Elements elements = document.getElementsByClass("product-list product-list-js product-list-position-relative");
            words = elements.text();
            tv_webSiteVal.setText(words);
            return null;
        }
    }
}