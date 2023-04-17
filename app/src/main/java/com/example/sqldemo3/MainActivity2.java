package com.example.sqldemo3;

//guidance from https://www.youtube.com/watch?v=PgkNC7AneKI

//Jsoup.connect("https://www.ebay.co.uk/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=xbox").get().text();
//
//
//        Jsoup.connect("https://www.very.co.uk/xbox").get().text();
//
//        Jsoup.connect("https://www.truffleshuffle.co.uk/cartoons/disney").get().text();
//
//        https://www.zavvi.com/elysium.search?search=disney
//
//        https://www.secretsales.com/search/shop-by/q/vans/
//
//        Jsoup.connect("https://www.homebase.co.uk/storage-home/furniture/sofas-sofa-beds.list?search=sofa").get().text();
//
//        Jsoup.connect("https://www.dunelm.com/search?q=shelves").get().text();
//
//        Jsoup.connect("https://www.homebargains.co.uk/search.aspx?searchterms=soap").get().text();
//
//        Jsoup.connect("https://www.wilko.com/en-uk/search/?text=sofa").get().text();
//        Jsoup.connect("https://www.houseoffraser.co.uk/searchresults?descriptionfilter=coat").get().text();
//        Jsoup.connect("https://www.johnlewis.com/search?search-term=ted%20baker").get().text();
//        Jsoup.connect("https://www.urbanoutfitters.com/en-gb/search?q=weed").get().text();
//        Jsoup.connect("https://365games.co.uk/search.php?search_query=disney&section=product").get().text();

//guidance from https://www.youtube.com/watch?v=PgkNC7AneKI

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    //Creating the objects of textview and button
    Button btn_webSite;
    EditText edt_searchValues;
    ListView lv_elements;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayList;
    Button btn_Home;
    Button btn_Logout;
    Button btn_toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Linking this up with the activitymain2 xml
        //tv_webSiteVal = findViewById(R.id.tv_webValue);

        //Making textview scrollable as there is a lot of text
        //tv_webSiteVal.setMovementMethod(new ScrollingMovementMethod());;
        btn_webSite = findViewById(R.id.btn_getWeb);
        edt_searchValues = findViewById(R.id.editSearchProducts);
        lv_elements = findViewById(R.id.lv_elements);
        arrayList = new ArrayList<String>();
        btn_Home =  findViewById(R.id.btn_TakeToHome);
        btn_Logout = findViewById(R.id.btn_Logout);
        btn_toLogin = findViewById(R.id.btn_TakeToLogin);

        //when button is clicked then call the doIt method
        btn_webSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new doIT().execute();
                arrayAdapter = new ArrayAdapter(MainActivity2.this, android.R.layout.simple_list_item_1, arrayList);
                lv_elements.setAdapter(arrayAdapter);
            }
        });

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Taking you from main activity to mainactivity 3
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        btn_toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

    }

    public class doIT extends AsyncTask<Void,Void,Void> {
        String words;
        private WebView webView;

        @Override
        protected Void doInBackground(Void... params) {

            //I used jsoup for this  jsoup is ........


            //Using jsoup libray to help with the coding -- into gradle dependency
            //org.jsoup.nodes.Document document = null;
            org.jsoup.nodes.Document zavviDoc = null;
            org.jsoup.nodes.Document truffleShuffleDoc = null;
            org.jsoup.nodes.Document secretSalesDoc = null;
            Document dunelmDoc;
            Document homeBargainsDoc;
            Document wilkoDoc;
            Document houseOfFraserDoc;
            Document johnLewisDoc;
            Document urbanOutfittersDoc;
            Document games365Doc;
            Document ebayDoc;


            try {

                String value = edt_searchValues.getText().toString();
                //calling the url, and having access to the internet through the use of jsoup
                //have added permission into manifest file to allow app to use the internet
                // document = Jsoup.connect("https://www.vans.co.uk/shop/en-gb/vans-gb/women-shoes").get();
                //document =  Jsoup.connect("https://www.very.co.uk/" + value).get();



                zavviDoc = Jsoup.connect("https://www.zavvi.com/elysium.search?search=" + value).get();
                //arrayAdapter = new ArrayAdapter(MainActivity2.this, android.R.layout.simple_list_item_1, arrayList);

                String zavviResults = zavviDoc.getElementsByClass("productListProducts_product").text();
                Elements zavviFirstValue = zavviDoc.getElementsByClass("productListProducts_product");
                if (zavviDoc != null && zavviFirstValue.size() > 0) {
                    zavviFirstValue.get(0);
                    arrayList.add(zavviFirstValue.get(0).text());
                } else {
                    return null;
                }


//               truffleShuffleDoc  = Jsoup.connect("https://www.truffleshuffle.co.uk/cartoons/" + value).get();
//
//               String truffleShuffleValues = truffleShuffleDoc.getElementsByClass("searchResult").text();

                secretSalesDoc  = Jsoup.connect(" https://www.secretsales.com/search/shop-by/q/" + value + "/").get();

                String secretSalesValues = secretSalesDoc.getElementsByClass("c-listings__item").text();
                Elements secretFirstValue = secretSalesDoc.getElementsByClass("c-listings__item");
                if (secretSalesDoc != null && secretFirstValue.size() > 0) {
                    secretFirstValue.get(0);
                    arrayList.add(secretFirstValue.get(0).text());

                } else {
                    return null;
                }
//
                dunelmDoc  = Jsoup.connect("https://www.dunelm.com/search?q=" + value).get();

                String dunelmValues = dunelmDoc.getElementsByClass("e19x7edn1 dw-qoox9g-A--Link-StyleLessLink--SearchResultProductCard e1s4u2np0").text();
                Elements dunelmFirstValue = dunelmDoc.getElementsByClass("e19x7edn1 dw-qoox9g-A--Link-StyleLessLink--SearchResultProductCard e1s4u2np0");
                if (dunelmDoc != null && dunelmFirstValue.size() > 0) {
                    dunelmFirstValue.get(0);
                    arrayList.add(dunelmFirstValue.get(0).text());
                } else {
                    return null;
                }

                homeBargainsDoc  = Jsoup.connect("https://www.homebargains.co.uk/search.aspx?searchterms=" + value).get();

                String homeBargainsValues = homeBargainsDoc.getElementsByClass("item-box").text();
                Elements homeFirstValue = homeBargainsDoc.getElementsByClass("item-box");
                if (homeBargainsDoc != null && homeFirstValue.size() > 0) {
                    homeFirstValue.get(0);
                    arrayList.add(homeFirstValue.get(0).text());
                } else {
                    return null;
                }

                wilkoDoc  = Jsoup.connect("https://www.wilko.com/en-uk/search/?text=" + value).get();

                String wilkoValues = wilkoDoc.getElementsByClass("product-item js-product-data").text();
                Elements wilkoFirstValue = wilkoDoc.getElementsByClass("product-item js-product-data");
                if (wilkoDoc != null && wilkoFirstValue.size() > 0) {
                    wilkoFirstValue.get(0);
                    arrayList.add(wilkoFirstValue.get(0).text());
                } else {
                    return null;
                }

//                houseOfFraserDoc  = Jsoup.connect("https://www.houseoffraser.co.uk/searchresults?descriptionfilter=" + value).get();
//                houseOfFraserDoc = Jsoup.connect("https://www.houseoffraser.co.uk/brand/" + value).get();
//                String houseOfFraserValues = houseOfFraserDoc.getElementsByClass("s-productscontainer2 plp-products-container frasers-plus-force-flex").text();
//                if (houseOfFraserDoc != null && houseOfFraserDoc.getElementsByClass("s-productscontainer2 plp-products-container frasers-plus-force-flex").size() > 0) {
//                    houseOfFraserDoc.getElementsByClass("s-productscontainer2 plp-products-container frasers-plus-force-flex").get(0);
//
//                } else {
//                    return null;
//                }
//
                johnLewisDoc  = Jsoup.connect("https://www.johnlewis.com/search?search-term=" + value).get();

                String johnLewisValues = johnLewisDoc.getElementsByClass("ProductGrid_product-grid__product__oD7Jq").text();
                Elements johnFirstValue = johnLewisDoc.getElementsByClass("ProductGrid_product-grid__product__oD7Jq");
                if(johnLewisDoc != null && johnFirstValue.size() > 0){
                    johnFirstValue.get(0);
                    arrayList.add(johnFirstValue.get(0).text());
                }else{
                    return null;
                }

                urbanOutfittersDoc  = Jsoup.connect("https://www.urbanoutfitters.com/en-gb/search?q=" + value).get();

                String urbanOutfittersValues = urbanOutfittersDoc.getElementsByClass("c-pwa-tile-grid-inner").text();
                Elements urbanFirstValue = urbanOutfittersDoc.getElementsByClass("c-pwa-tile-grid-inner");
                if(urbanOutfittersDoc != null && urbanFirstValue.size() > 0){
                    urbanFirstValue.get(0);
                    arrayList.add(urbanFirstValue.get(0).text());
                }else {
                    return null;
                }

                games365Doc = Jsoup.connect("https://365games.co.uk/search.php?search_query=" + value + "&section=product").get();

                String games365Values = games365Doc.getElementsByClass("product").text();
                Elements gameFirstValue = games365Doc.getElementsByClass("product");
                if(games365Doc != null && gameFirstValue.size() > 0){
                    gameFirstValue.get(0);
                    arrayList.add(gameFirstValue.get(0).text());
                }else {
                    return null;
                }

                ebayDoc = Jsoup.connect("https://www.ebay.co.uk/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=" + value ).get();

                String ebayValues = ebayDoc.getElementsByClass("s-item__wrapper clearfix").text();
                Elements ebayFirstValue = ebayDoc.getElementsByClass("s-item__wrapper clearfix");
                if(ebayDoc != null && ebayFirstValue.size() > 0){
                    ebayFirstValue.get(3);
                    //starts from 3 due to website layout
                    arrayList.add(gameFirstValue.get(0).text());
                }else {
                    return null;
                }
//                Connection connection = Jsoup.connect("https://www.office.co.uk/brand/");
//
//                //specify user agent
//                connection.userAgent("Mozilla/5.0");
//
//                //get the HTML document
//                Document doc = connection.get();
//
//
//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.loadUrl("https://www.office.co.uk/brand/crocs");
//                document = Jsoup.connect("https://www.office.co.uk/brand/crocs").get();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"No Results Found", Toast.LENGTH_SHORT).show();
            }
//            arrayAdapter = new ArrayAdapter(MainActivity2.this, android.R.layout.simple_list_item_1, arrayList);
//            lv_elements.setAdapter(arrayAdapter);

            //using the jsoup to get product list that is returned from the url above
            //org.jsoup.select.Elements elements = document.getElementsByClass("product-list product-list-js product-list-position-relative");

//            org.jsoup.select.Elements elements = document.getElementsByClass("product-block-info info info-js");
//            org.jsoup.select.Elements elements = document.getElementsByClass("col-s-12 col-m-8 col-1-6");


            //making it the text and adding that text to the textview box so we can see it
            //words = elements.text();
            //tv_webSiteVal.setText(words);
            return null;
        }
    }

}