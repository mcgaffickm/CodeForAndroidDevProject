package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.gesture.Gesture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
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

public class SecretChamber extends Fragment implements GestureDetector.OnDoubleTapListener, SensorEventListener {

    MainActivity mainActivity;
    Button btnsTakes[];
    boolean dark;

    SensorManager sensorManager;
    Sensor light;
    float maxValue;
    GestureDetectorCompat detect;

    LinearLayout secretBright, secretDark;

    public SecretChamber() {
        btnsTakes = new Button[4];
    }

    public void insertActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_secret_chamber, container, false);
        btnsTakes[0] = v.findViewById(R.id.btnTakeGreen);
        btnsTakes[1] = v.findViewById(R.id.btnTakeGreen3);
        btnsTakes[2] = v.findViewById(R.id.btnTakeGreen4);
        btnsTakes[3] = v.findViewById(R.id.btnTakeGreenReal);

        for(int i = 0; i < btnsTakes.length; i++)
        {
            btnsTakes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.btnTakeGreenReal) {
                        Toast.makeText(getContext(), "Correct: You have received the correct green orb!", Toast.LENGTH_SHORT).show();
                        mainActivity.addOrbs(1);
                        v.setVisibility(View.INVISIBLE);
                    } else {
                        mainActivity.subtractLives();
                        Toast.makeText(getContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if(!mainActivity.getOptions().isLightSensor())
            detect.setOnDoubleTapListener(this);
        else
        {
            sensorManager = (SensorManager) mainActivity.getSystemService(Context.SENSOR_SERVICE);
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            maxValue = light.getMaximumRange();
        }

        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_FASTEST);

        secretBright = v.findViewById(R.id.laySecretBright);
        secretDark = v.findViewById(R.id.laySecretDark);

        return v;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if(dark) {
            secretDark.setVisibility(View.GONE);
            secretBright.setVisibility(View.VISIBLE);
            dark = false;
        }
        else if (!dark) {
            secretDark.setVisibility(View.VISIBLE);
            secretBright.setVisibility(View.GONE);
            dark = true;
        }
        return true;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float lightLevel = event.values[0];

        float pivot = 50; //Will need to experiment later

        Toast.makeText(getContext(), "Current Light Level: " +  lightLevel, Toast.LENGTH_SHORT).show();
        if (lightLevel > pivot)
        {
            if (!dark) {
                secretDark.setVisibility(View.VISIBLE);
                secretBright.setVisibility(View.GONE);
                dark = true;
            }
        }
        else
        {
            if(dark) {
                secretDark.setVisibility(View.GONE);
                secretBright.setVisibility(View.VISIBLE);
                dark = false;
            }
        }
    }

    //Stuff that needed to be included for interfaces

    //Don't care about this
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
