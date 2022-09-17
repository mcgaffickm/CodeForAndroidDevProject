package com.example.secretsofthecursedcastle;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


public class Tower extends Fragment {


    Switch sw1, sw2, sw3, sw4;
    Button btnTowerCheck;

    boolean best;

    MainActivity activity;

    public Tower() {
    }

    public void insertActivity(MainActivity activity)
    {
        this.activity = activity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tower, container, false);

        best = false;
        sw1 = v.findViewById(R.id.swTower1);
        sw2 = v.findViewById(R.id.swTower2);
        sw3 = v.findViewById(R.id.swTower3);
        sw4 = v.findViewById(R.id.swTower4);

        if(activity.isBest())
        {
            Toast.makeText(getContext(), "Turn all switches on to change history!", Toast.LENGTH_SHORT).show();
        }

        btnTowerCheck = v.findViewById(R.id.btnTowerComplete);

        btnTowerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean complete = true;
                if(!best) {
                    if (sw1.isChecked() && sw2.isChecked() && sw3.isChecked() && sw4.isChecked()) {
                        best = true;
                        activity.checkCount();
                        Toast.makeText(getContext(), "Setting Course for New History.", Toast.LENGTH_SHORT).show();
                    }
                }
                if(sw1.isChecked())
                {
                    complete = false;
                }
                if(!sw2.isChecked())
                {
                    complete = false;
                }
                if(!sw3.isChecked())
                {
                    complete = false;
                }
                if(sw4.isChecked())
                {
                    complete = false;
                }

                if(complete)
                {
                    Toast.makeText(getContext(), "Correct:  A secret compartment opens and you find a blue orb", Toast.LENGTH_SHORT).show();
                    activity.addOrbs(2);
                    btnTowerCheck.setVisibility(View.INVISIBLE);
                }
                else
                {
                    activity.subtractLives();
                    Toast.makeText(getContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }
}
