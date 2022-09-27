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

    //Declares widgets and stuff like usual
    List<Crest> crests;
    Button btnSubmit;
    ListView lv;
    MainActivity activity;
    boolean finish;

    public KingRoom() {
        //Sets up the list and default values
        finish = false;
        crests = new ArrayList<>();
        crests.add(new Crest("Mcgillis", "500s","A house crest from the first ruling family of this land.", R.drawable.crest1, 1));
        crests.add(new Crest("Alisters", "800s","House crest for the family who brought about peace to the realm by defeating neighboring countries.", R.drawable.crest2, 2));
        crests.add(new Crest("Willchesters", "1200s","The house crest of the family who renovated the castle into what it currently is.", R.drawable.crest3, 4));
        crests.add(new Crest("O. Count.", "1800s","The most recent house crest with the symbol for the most recent king.", R.drawable.crest4, 3));
    }

    //Insert activity
    public void insertActivity(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_king_room, container, false);

        //Finds the list view
        lv = v.findViewById(R.id.lvKingRoom);

        //Toast if you are on best route
        if(activity.isBest())
            Toast.makeText(getContext(), "Change the button in one crest twenty times to change history!", Toast.LENGTH_SHORT).show();


        //Finds the btn and kills it if you already finished the task
        btnSubmit = v.findViewById(R.id.btnKingComplete);
        if(finish)
            btnSubmit.setVisibility(View.INVISIBLE);

        //Sets the click listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Determines if the puzzles are correct using the main activity list
                boolean complete = true;
                for(int i = 0; i < 4; i++)
                {
                    if(!activity.kingPuzzleCorrect[i])
                    {
                        complete = false;
                    }
                }
                //Correct answer - get decode or wrong answer - 1 heart
                if(!complete)
                {
                    activity.subtractLives();
                    Toast.makeText(getContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Correct: A decoder device pops out from a compartment in the wall.  You take it.", Toast.LENGTH_SHORT).show();
                    activity.setAcquireTools(3);
                    finish = true;
                    btnSubmit.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Sets the adapter and makes everything work in it
        KingListAdapter kla = new KingListAdapter(getContext(), crests);
        kla.insertActvity(activity);
        lv.setAdapter(kla);
        return v;
    }
}
