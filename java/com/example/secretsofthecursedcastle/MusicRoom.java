package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MusicRoom extends Fragment {
    public MusicRoom() {
        // Required empty public constructor
    }
    Spinner musicChoices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_music_room, container, false);
        String musics[] = {"1","2", "3","4"};
        musicChoices = v.findViewById(R.id.spinMusic);
        musicChoices.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, musics));


       return v;
    }

    public void changeMusic(View view)
    {
        return;
    }

}
