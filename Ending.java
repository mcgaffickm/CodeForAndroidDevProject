package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Ending extends AppCompatActivity {

    //Declares the widgets and stuff as usual
    TextView title, description;
    ImageView imgEnding;
    LinearLayout background;
    SwipeRefreshLayout sw;
    Intent restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        //Declares the way to return to beginning
        restart = new Intent(Ending.this, SplashScreen.class);

        //Finds all the widgets and sets the values to everything based on your ending
        background = findViewById(R.id.layEnding);
        background.setBackgroundResource(EndingChanger.background);

        imgEnding = findViewById(R.id.imgEnding);
        imgEnding.setImageResource(EndingChanger.endImage);

        title = findViewById(R.id.lblEnding);
        title.setText(EndingChanger.title);

        description = findViewById(R.id.txtEnding);
        description.setText(EndingChanger.description);

        //Swipe to go to the beginning
        sw = findViewById(R.id.endRefresh);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Ending.this, "Time Travel Procedure Initiated!", Toast.LENGTH_SHORT).show();
                startActivity(restart);
            }
        });
    }

    @Override
    public void onBackPressed() {
     Toast.makeText(this, "No cheating!", Toast.LENGTH_SHORT).show();
    }
}
