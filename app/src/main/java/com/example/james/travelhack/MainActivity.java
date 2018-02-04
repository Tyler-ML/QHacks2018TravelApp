package com.example.james.travelhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSwipeActivity();
            }
        });
    }
    //Tyler's pretty great.
    public void openSwipeActivity(){
    Intent intent = new Intent(this,SwipeActivity.class);
    startActivity(intent);
    }

    public String getWebPage(String city){
        city.replace(' ', '+');
        city = "https://www.google.ca/search?q=site%3Atripadvisor.ca+" + city + "+things+to+do";
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL(city).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        System.out.println(content);
        String[] webpageHTML = content.split("\n");
        for(int index = 0; index < webpageHTML.length; index++){
            if(webpageHTML[index].contains("href=\"https://www.tripadvisor.ca/Attractions-")){
                return(webpageHTML[index].substring(webpageHTML[index].indexOf("https://www.")
                        , webpageHTML[index].indexOf("\"", webpageHTML[index].indexOf("https://www."))));
            }
        }
        return city;
    }
    public String getURL(String inputLocation) {
        URL url;
        String finalURL = "";
        try {
            String a="https://www.google.ca/search?q=site%3Atripadvisor.ca+"+ inputLocation +"+things+to+do";
            url = new URL(a);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                if(inputLine.contains("https://www.tripadvisor.ca/")){
                    finalURL = inputLine.substring(inputLine.indexOf("https://www.trip"), inputLine.indexOf("\"", inputLine.indexOf("https://www.trip") + 1));
                    break;
                }
            }
            br.close();
            System.out.println("/Done");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalURL;
    }

}
