/*
package com.example.james.travelhack;


import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Nuliflyer's Account on 2/3/2018.
 */
/*
public class NetworkThread extends AsyncTask<Void, Integer, String[]>{
    String url;
    NetworkThread(String url){

        this.url = url;
    }


    protected String[] doInBackground(Void... params){

            String content = null;
            URLConnection connection = null;
            try {
                connection = new URL(getURL(url)).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content = scanner.next();
                System.out.println("Successfully Read Webdata.");
                System.out.println(content.substring(0, 100));
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Unsuccessfully Read Webdata.");
            }

            String[] data = content.split("\n");
            String[] locations = new String[data.length];
            int locationsCount = 0;

            for (int index = 0; index < data.length; index++) {
                //Technically could lose some locations because of the not contains POI.
                if (data[index].contains("<img alt") && !data[index].contains("poi")) {
                    locations[locationsCount] = data[index].substring(data[index].indexOf('"') + 1, data[index].indexOf('"', data[index].indexOf('"') + 1));
                    locationsCount++;
                }
            }
            for (int index = 0; index < locations.length - 1; index++) {
                if (locations[index] != null && locations[index].contains("&#39;")) {
                    locations[index] = locations[index].substring(0, locations[index].indexOf("&#39;")) + "'" + locations[index].substring(locations[index].indexOf("&#39;") + 5, locations[index].length());
                }
            }
            //Tyler's pretty great
            return locations;

    }

    protected void onPreExecute(String word){
        //If we want to change any elements we can here.
        //eg: progressDialog.setMax(10);
    }

    protected void onPostExecute(String[] locationsList){
        String dataString = "";
        for(int index = 0; index < locationsList.length; index++){
            if(locationsList[index] == null){ continue;}
            dataString += locationsList[index] + "#";
        }


    }

    protected void onProgressUpdate(){
        //We'll need this if we want a loading bar or smtn;
    }
    public String getURL(String inputLocation) {
        URL url;
        String finalURL = "Oakville";
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
            System.out.println("Done");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalURL;
    }

}*/