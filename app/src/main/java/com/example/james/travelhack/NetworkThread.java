package com.example.james.travelhack;


import android.os.AsyncTask;
import android.widget.TextView;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Nuliflyer's Account on 2/3/2018.
 */

public class NetworkThread extends AsyncTask<Void, Integer, String[]>{
    String url;
    TextView textView;
    NetworkThread(String url, TextView textView){
        this.textView = textView;
        this.url = url;
    }


    protected String[] doInBackground(Void... params){

            String content = null;
            URLConnection connection = null;
            try {
                connection = new URL(url).openConnection();
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
        System.out.println("me first plz");
        textView.setText(dataString);
    }

    protected void onProgressUpdate(){
        //We'll need this if we want a loading bar or smtn;
    }
}