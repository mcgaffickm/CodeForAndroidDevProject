package com.example.secretsofthecursedcastle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero_splash_screen);

        //Runs the thread and when done, get to go to the title
        Thread timer = new Thread()
        {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (Exception e){}
                Intent i = new Intent(SplashScreen.this, TitleScreen.class);

                //Makes a default options for first time playing
                SelectedOptions options = new SelectedOptions();
                i.putExtra("Options", options);
                startActivity(i);
                }
        };
        timer.start();
    }
}
