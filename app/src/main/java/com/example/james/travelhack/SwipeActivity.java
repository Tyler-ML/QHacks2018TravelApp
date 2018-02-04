package com.example.james.travelhack;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import butterknife.BindView;
import butterknife.ButterKnife;

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
    private String[] newArr = new String[4];
    private TextView textview;

    //@Nullable @BindView(R.id.frame) SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        button = findViewById(R.id.button);
        textview = findViewById(R.id.textView2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openILActivity();
            }
        });

        Intent intent = getIntent();
        al = new ArrayList<String>();
        String[] locations = intent.getStringArrayExtra("locations");
        for(int index = 1; index < locations.length; index++){
            al.add(locations[index]);
        }

        //Marcus is better.

        System.out.println(textview.getText().toString() + "me 2nd plz");
        //al.add("London Eye");
        //al.add("Buckingham Palace");
        //al.add("Abbey Road");
        //al.add("Big Ben");
        //al.add("Teen titans tower");
        //al.add("c++");
        //al.add("css");
        //al.add("javascript");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );

        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
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
                //Toast.makeText(SwipeActivity.this, "left" ,Toast.LENGTH_SHORT).show();
            }
            //arbituary value greater than the limit of three destinations
            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(SwipeActivity.this, "right" ,Toast.LENGTH_SHORT).show();

                if (counter < 4){

                    newArr[counter] = (String) dataObject;
                }

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
        findViewById(R.id.imageButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectRight();
            }
        });

        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectLeft();
            }
        });

    }
    public void openILActivity(){
        Intent i = new Intent(this,List.class);

        i.putExtra("selections", newArr);
        startActivity(i);
        finishAfterTransition();
    }

    public static int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1)
            pos = str.indexOf(substr, pos + 1);
        return pos;
    }

}