package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MusicRoom extends Fragment {
    public MusicRoom() {
        // Required empty public constructor
    }
    Spinner musicChoices;

    MainActivity activity;

    Button btnChange;

    public void insertActivity(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_music_room, container, false);
        String musics[] = {"1. Court and Page","2. Echoes of Time", "3. Strange Ways","4. Day of Chaos"};
        musicChoices = v.findViewById(R.id.spinMusic);
        musicChoices.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, musics));

        btnChange = v.findViewById(R.id.btnMusic);
        btnChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int song = 0;
                if(musicChoices.getSelectedItem().toString().charAt(0) == '1') {
                    Toast.makeText(getContext(), "Playing Court and Page by Silent Partner", Toast.LENGTH_SHORT).show();
                    song = 0;
                }
                if(musicChoices.getSelectedItem().toString().charAt(0) == '2')
                {
                    Toast.makeText(getContext(), "Playing Echoes of Time by Kevin MacLeod", Toast.LENGTH_SHORT).show();
                    song = 1;
                }
                if(musicChoices.getSelectedItem().toString().charAt(0) == '3')
                {
                    Toast.makeText(getContext(), "Strange Ways by Silent Partner", Toast.LENGTH_SHORT).show();
                    song = 2;
                }
                if(musicChoices.getSelectedItem().toString().charAt(0) == '4')
                {
                    Toast.makeText(getContext(), "Day of Chaos by Kevin MacLeod", Toast.LENGTH_SHORT).show();
                    song = 3;
                }

                activity.changeSong(song);
            }
        });

       return v;
    }

}
