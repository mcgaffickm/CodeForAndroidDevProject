package com.example.secretsofthecursedcastle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class KingRoom extends Fragment {

    List<Crest> crests;

    Button btnSubmit;

    ListView lv;

    MainActivity activity;

    public KingRoom() {
        crests = new ArrayList<>();
        crests.add(new Crest("Mcgillis", "500s","A house crest from the first ruling family of this land.", R.drawable.crest1, 1));
        crests.add(new Crest("Alisters", "800s","House crest for the family who brought about peace to the realm by defeating neighboring countries.", R.drawable.crest2, 2));
        crests.add(new Crest("Willchesters", "1200s","The house crest of the family who renovated the castle into what it currently is.", R.drawable.crest3, 4));
        crests.add(new Crest("O. Count.", "1800s","The most recent house crest with the symbol for the most recent king.", R.drawable.crest4, 3));
    }

    public void insertActivity(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_king_room, container, false);
        lv = v.findViewById(R.id.lvKingRoom);
        if(activity.isBest())
        {
            Toast.makeText(getContext(), "Change the button in one crest twenty times to change history!", Toast.LENGTH_SHORT).show();
        }
        btnSubmit = v.findViewById(R.id.btnKingComplete);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean complete = true;
                for(int i = 0; i < 4; i++)
                {
                    if(!activity.kingPuzzleCorrect[i])
                    {
                        complete = false;
                    }
                }
                if(!complete)
                {
                    activity.subtractLives();
                    Toast.makeText(getContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Correct: A decoder device pops out from a compartment in the wall.  You take it.", Toast.LENGTH_SHORT).show();
                    activity.setAcquireTools(2);
                    btnSubmit.setVisibility(View.INVISIBLE);
                }
            }
        });
        KingListAdapter kla = new KingListAdapter(getContext(), crests);
        kla.insertActvity(activity);
        lv.setAdapter(kla);
        return v;
    }
}
