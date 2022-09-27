package com.example.secretsofthecursedcastle;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class AlchemyLab extends Fragment {

    //Declares widgets and stuff needed for functions
    VideoView vd;
    MediaController mc;

    Button b1, b2, b3, b4;

    //One of few locations that could be new everytime
    public AlchemyLab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alchemy__lab, container, false);

        //Finds all the widgets and sets up the video materials
        mc = new MediaController(getContext());
        vd = v.findViewById(R.id.vidHallucination);
        vd.setMediaController(mc);
        mc.setAnchorView(vd);

        //Button widgets
        b1 = v.findViewById(R.id.btnDrink1);
        b2 = v.findViewById(R.id.btnDrink2);
        b3 = v.findViewById(R.id.btnDrink3);
        b4 = v.findViewById(R.id.btnDrink4);

        //Sets on click listeners to play a specific video based on the button pushed
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.greenorb;
                Uri vidUri = Uri.parse(path);
                vd.setVideoURI(vidUri);
                vd.start();
            }
        });

        //Button 2
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.redorb;
                Uri vidUri = Uri.parse(path);
                vd.setVideoURI(vidUri);
                vd.start();
            }
        });

        //Button 3
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.bluetorchfire;
                Uri vidUri = Uri.parse(path);
                vd.setVideoURI(vidUri);
                vd.start();
            }
        });

        //Button 4
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.blueorb;
                Uri vidUri = Uri.parse(path);
                vd.setVideoURI(vidUri);
                vd.start();
            }
        });

        return v;
    }
}
