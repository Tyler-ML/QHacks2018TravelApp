package com.example.james.travelhack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class SwipeActivity extends AppCompatActivity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private Button button;
    private ArrayList<Object> toGO;
    private String[] newArr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openILActivity();
            }
        });
        String[] data = new String[0];
        NetworkThread networkThread = new NetworkThread(data, "https://www.tripadvisor.ca/Attractions-g155019-Activities-Vancouver_British_Columbia.html");

        try {
            data = networkThread.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Marcus is better.
        al = new ArrayList<String>();
        //ArrayList<String> al = new ArrayList<String>(Arrays.asList(data));
        //for(int index = 0; index < data.length - 1; index++){
          //  al.add(data[index]);
        //}

        al.add("London Eye");
        al.add("Buckingham Palace");
        al.add("Abbey Road");
        al.add("Big Ben");
        al.add("Teen titans tower");
        al.add("c++");
        al.add("css");
        al.add("javascript");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        toGO = new ArrayList<>();
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            int counter = 0;
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(SwipeActivity.this, "left" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(SwipeActivity.this, "right" ,Toast.LENGTH_SHORT).show();
               newArr[counter] = (String) dataObject;
               counter++;
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(SwipeActivity.this, "Quit" ,Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void openILActivity(){
        String g = "text me";
        Intent intent2 = new Intent(this,IteneraryListActivity.class);
        intent2.putExtra("GeeGee", newArr);
        startActivity(intent2);
    }

}