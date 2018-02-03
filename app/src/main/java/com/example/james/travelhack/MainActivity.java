package com.example.james.travelhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkThread networkThread = new NetworkThread("https://www.tripadvisor.ca/Attractions-g154943-Activities-Vancouver_British_Columbia.html");
        networkThread.execute();
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSwipeActivity();
            }
        });
    }
    public void openSwipeActivity(){
    Intent intent = new Intent(this,SwipeActivity.class);
    startActivity(intent);
    }
}
