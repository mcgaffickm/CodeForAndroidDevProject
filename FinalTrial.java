package com.example.secretsofthecursedcastle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class FinalTrial extends Fragment {

    //Declares the variables and activities, widgets
    MainActivity mainActivity;

    Boolean correct[];
    Button lit[];
    ImageView torch[];

    int count;

    //Constructor
    public FinalTrial() {
        correct = new Boolean[3];
        correct[0]= false;
        correct[1]= false;
        correct[2]= false;
        lit = new Button[4];
        torch = new ImageView[4];
        count = 0;
    }

    //Inserts the activity
    public void insertActivity(MainActivity mainActivity)
    { this.mainActivity = mainActivity; }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_final_trial, container, false);

        //Sets the widgets as needed
        lit[0] = v.findViewById(R.id.btnTorchGreen);
        torch[0] = v.findViewById(R.id.imgGreenTorch);
        lit[1] = v.findViewById(R.id.btnTorchBlue);
        torch[1] = v.findViewById(R.id.imgBlueTorch);
        lit[2] = v.findViewById(R.id.btnTorchRed);
        torch[2] = v.findViewById(R.id.imgRedTorch);
        lit[3] = v.findViewById(R.id.btnTorchOrange);
        torch[3] = v.findViewById(R.id.imgOrangeTorch);

        //Changes anything that the player has already done if they left and came back
        if(correct[0]) {
            torch[3].setImageResource(R.drawable.torch);
            lit[3].setVisibility(View.INVISIBLE);
        }

        if(correct[1]) {
            torch[2].setImageResource(R.drawable.redtorch);
            lit[2].setVisibility(View.INVISIBLE);
        }

        if(correct[2]) {
            torch[1].setImageResource(R.drawable.bluetorch);
            lit[1].setVisibility(View.INVISIBLE);
        }


        //Loops and sets a listener for every button
        for(int i = 0; i < lit.length; i++)
        {
            lit[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Determines which id is clicked and if it is the right order
                    //Goes to end if you get them right, worst end if wrong
                    switch (v.getId())
                    {
                        case R.id.btnTorchGreen:
                            if(count == 3)
                            {
                                torch[0].setImageResource(R.drawable.greentorch);
                                v.setVisibility(View.INVISIBLE);
                                mainActivity.finishGame();
                            }
                            else
                            {
                                 mainActivity.gameOver();
                            }
                            break;
                        case R.id.btnTorchBlue:
                            if(count == 2)
                            {
                                count++;
                                correct[2] = true;
                                torch[1].setImageResource(R.drawable.bluetorch);
                                v.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                mainActivity.gameOver();
                            }
                            break;
                        case R.id.btnTorchRed:
                            if(count == 1)
                            {
                                count++;
                                correct[1] = true;
                                torch[2].setImageResource(R.drawable.redtorch);
                                v.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                mainActivity.gameOver();
                            }
                            break;
                        case R.id.btnTorchOrange:
                                count++;
                                correct[0] = true;
                                torch[3].setImageResource(R.drawable.torch);
                                v.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            });

        }

        return v;
    }
}
