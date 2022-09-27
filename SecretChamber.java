package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SecretChamber extends Fragment implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener,  SensorEventListener {

    //Activity, buttons, variables, etc declaration
    MainActivity mainActivity;
    Button btnsTakes[];
    boolean dark;

    SensorManager sensorManager;
    Sensor light;
    float maxValue;
    GestureDetectorCompat detect;

    boolean finish;

    LinearLayout secretBright, secretDark;

    //Constructor with stuff that needs to be same
    public SecretChamber() {
        finish = false;
        btnsTakes = new Button[4];
    }

    //Insert the activity
    public void insertActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_secret_chamber, container, false);

        //Finds the button views
        btnsTakes[0] = v.findViewById(R.id.btnTakeGreen);
        btnsTakes[1] = v.findViewById(R.id.btnTakeGreen3);
        btnsTakes[2] = v.findViewById(R.id.btnTakeGreen4);
        btnsTakes[3] = v.findViewById(R.id.btnTakeGreenReal);

        //Dark is default setting
        dark = true;

        //If done, buttons disappear
        if(finish) {
            for (int i = 0; i < btnsTakes.length; i++) {
                btnsTakes[i].setVisibility(View.INVISIBLE);
            }
        }

        //Sets click listener for buttons that are there
        for(int i = 0; i < btnsTakes.length; i++)
        {
            btnsTakes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //If you hit the real one, you get an orb.  IF not, lose a life
                    if (v.getId() == R.id.btnTakeGreenReal) {
                        Toast.makeText(getContext(), "Correct: You have received the correct green orb!", Toast.LENGTH_SHORT).show();
                        mainActivity.addOrbs(1);
                        finish = true;
                        v.setVisibility(View.INVISIBLE);
                    } else {
                        //Stops the sensor if game over
                        if(mainActivity.lives == 1)
                            stopListener();
                        mainActivity.subtractLives();
                        Toast.makeText(getContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //Sets up gesture mode if enabled or light sensor if enabled
        if(!mainActivity.getOptions().isLightSensor()) {
            //Gesture mode double tap stuff
            Toast.makeText(getContext(), "Touch Based Enabled", Toast.LENGTH_SHORT).show();
            detect = new GestureDetectorCompat(getContext(), this);
            detect.setOnDoubleTapListener(this);
            v.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {

                    if (detect.onTouchEvent(event)) {
                        return true;
                    } else {
                        SecretChamber.super.getActivity().onTouchEvent(event);
                    }
                    return true;
                }
            });
        }
        else
        {
            //Light sensor stuff
            sensorManager = (SensorManager) mainActivity.getSystemService(Context.SENSOR_SERVICE);
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            maxValue = light.getMaximumRange();
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_FASTEST);
        }

        //Finds the layouts finally and sets them in the default visibility
        secretBright = v.findViewById(R.id.laySecretBright);
        secretDark = v.findViewById(R.id.laySecretDark);

        secretBright.setVisibility(View.GONE);
        secretDark.setVisibility(View.VISIBLE);


        return v;
    }

    //Stops listener if you leave
    @Override
    public void onDestroyView() {
        stopListener();
        super.onDestroyView();
    }

    //Stop the listener for game over or changing room
    public void stopListener()
    {
        if(mainActivity.getOptions().isLightSensor())
            sensorManager.unregisterListener(this);
    }

    //Light sensor method (Puzzle v1)
    @Override
    public void onSensorChanged(SensorEvent event) {
        //The level received
        float lightLevel = event.values[0];

        float pivot = 50; //Requires decent lighting

        //Gives the player some indication 0 = bright, an increasing not zero is dark level
        Toast.makeText(getContext(), "Current Light Level: " +  lightLevel, Toast.LENGTH_SHORT).show();

        //If light level is bigger than the pivot than it goes dark
        if (lightLevel > pivot)
        {
            if (!dark) {
                secretDark.setVisibility(View.VISIBLE);
                secretBright.setVisibility(View.GONE);
                dark = true;
            }
        }
        //It goes light if bright enough
        else {
            if (dark) {
                secretDark.setVisibility(View.GONE);
                secretBright.setVisibility(View.VISIBLE);
                dark = false;
            }
        }
    }

    //Double tap listener stuff (Puzzle v2)
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //If you double tap it goes bright or dark depending on what it currently is
        if(dark) {
            Toast.makeText(getContext(), "Lighter Opened!", Toast.LENGTH_SHORT).show();
            secretDark.setVisibility(View.GONE);
            secretBright.setVisibility(View.VISIBLE);
            dark = false;
        }
        else if (!dark) {
            Toast.makeText(getContext(), "Lighter Closed!", Toast.LENGTH_SHORT).show();
            secretDark.setVisibility(View.VISIBLE);
            secretBright.setVisibility(View.GONE);
            dark = true;
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stuff that needed to be included for interfaces
    //Don't care about this stuff for my purposes
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
     return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
    return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
