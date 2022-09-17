package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class FinalTrial extends Fragment {

    MainActivity mainActivity;

    Button lit[];
    ImageView torch[];

    int count;

    public FinalTrial() {
        // Required empty public constructor
    }

    public void insertActivity(MainActivity mainActivity)
    {
       this.mainActivity = mainActivity;
       lit = new Button[4];
       torch = new ImageView[4];
       count = 0;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_final_trial, container, false);

        lit[0] = v.findViewById(R.id.btnTorchGreen);
        torch[0] = v.findViewById(R.id.imgGreenTorch);
        lit[1] = v.findViewById(R.id.btnTorchBlue);
        torch[1] = v.findViewById(R.id.imgBlueTorch);
        lit[2] = v.findViewById(R.id.btnTorchRed);
        torch[2] = v.findViewById(R.id.imgRedTorch);
        lit[3] = v.findViewById(R.id.btnTorchOrange);
        torch[3] = v.findViewById(R.id.imgOrangeTorch);

        for(int i = 0; i < lit.length; i++)
        {
            lit[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                                torch[1].setImageResource(R.drawable.greentorch);
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
                                torch[2].setImageResource(R.drawable.greentorch);
                                v.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                mainActivity.gameOver();
                            }
                            break;
                        case R.id.btnTorchOrange:
                                count++;
                                torch[3].setImageResource(R.drawable.greentorch);
                                v.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            });

        }

        return v;
    }
}
