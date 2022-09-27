package com.example.secretsofthecursedcastle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;


public class Cells extends Fragment {

    //Declares the calling activity
    MainActivity activity;

    //Declares the widget
    SeekBar location;
    ImageView cellWall;
    ImageButton btnCracked;
    Button btnImage;

    //Declares the variables
    int counter;
    boolean open;

    public Cells() {
        // Required empty public constructor
    }

    //The insert activity setting variables that need to always remain
    public void insertActivity(MainActivity activity)
    {
        counter = 10;
        this.activity = activity;
        open = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cells, container, false);

        //Finds the widgets
        location = v.findViewById(R.id.sbBar);
        cellWall = v.findViewById(R.id.imgCellWall);
        btnCracked = v.findViewById(R.id.imgBtnWall);
        btnImage = v.findViewById(R.id.btnView);

        //Sets the listener for the cracked wall
        btnCracked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Allows you to break the wall if you have a hammer and reveal the secret location
                if(activity.acquireTools[0]) {
                    counter--;
                    if (counter == 0) {
                        Toast.makeText(getContext(), "The wall breaks open, revealing a new path.", Toast.LENGTH_SHORT).show();
                        activity.openLocation(1);
                        open = true;
                        v.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(getContext(), "The wall shows signs of cracking, keep at it.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "You need some sort of tool to interact with this.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Sets the listener to get the image at the specific location
        btnImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = location.getProgress();

                //No need for this in any but 7
                btnCracked.setVisibility(View.INVISIBLE);

                //Determines the image to display when the seekbar is at the right position
                switch (i)
                {
                    case 1:
                        cellWall.setImageResource(R.drawable.sun);
                        break;
                    case 3:
                        cellWall.setImageResource(R.drawable.ocarving);
                        break;
                    case 4:
                        cellWall.setImageResource(R.drawable.blackhole);
                        break;
                    case 6:
                        cellWall.setImageResource(R.drawable.moon);
                        break;
                    case 9:
                        cellWall.setImageResource(R.drawable.star);
                        break;
                    case 7:
                        if(!open) {
                            btnCracked.setVisibility(View.VISIBLE);
                        }
                    default:
                        cellWall.setImageResource(R.drawable.carving);
                }
            }
        });

        return v;
    }


}
