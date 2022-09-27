package com.example.secretsofthecursedcastle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Inventory extends Fragment {

    //Declares activity, widgets, database, variables, whatever else is needed
    MainActivity mainActivity;

    ImageView orbG, orbR, orbB, ihammer, ilighter, heart1, heart2, heart3;

    int prog;

    LinearLayout linDecode, linMessage;

    ProgressBar progress;
    DecodeDBUtility db;

    Button btnDecode;
    EditText txtDecode;
    ListView scDecode;

    Boolean visibility[];

    List<String> inputs;
    List<String> outputs;

    //Assigns the default values to everything, constructor
    public Inventory() {
        inputs = new ArrayList<> ();
        outputs = new ArrayList<>();
        prog = 5;
        visibility = new Boolean[9];
        for(int i = 0; i < visibility.length; i++)
            visibility[i] = false;
    }

    //Inserts the activity
    public void insertActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_inventory, container, false);

        //Finds the views by their id as needed
        orbR = v.findViewById(R.id.imgRed);
        orbB = v.findViewById(R.id.imgBlue);
        orbG = v.findViewById(R.id.imgGreen);
        heart1 = v.findViewById(R.id.imgHeart1);
        heart2 = v.findViewById(R.id.imgHeart3);
        heart3 = v.findViewById(R.id.imgHeart3);
        ihammer = v.findViewById(R.id.imgHammer);
        ilighter = v.findViewById(R.id.imgLighter);
        progress = v.findViewById(R.id.progBar);
        progress.setProgress(prog);
        linDecode = v.findViewById(R.id.containDecoder);
        linMessage = v.findViewById(R.id.containMessages);
        btnDecode = v.findViewById(R.id.btnDecode);
        txtDecode = v.findViewById(R.id.txtDecode);
        scDecode = v.findViewById(R.id.scrPaper);

        //Creates database
        db = new DecodeDBUtility(getActivity());

        //Sets the visibility to start because I wanted it all visible in the IDE so I could design it
        orbR.setVisibility(View.INVISIBLE);
        orbG.setVisibility(View.INVISIBLE);
        orbB.setVisibility(View.INVISIBLE);
        heart1.setVisibility(View.VISIBLE);
        heart2.setVisibility(View.VISIBLE);
        heart3.setVisibility(View.VISIBLE);
        ihammer.setVisibility(View.INVISIBLE);
        ilighter.setVisibility(View.INVISIBLE);
        linMessage.setVisibility(View.INVISIBLE);
        linDecode.setVisibility(View.INVISIBLE);


        //Reactivate everything when needed
        //Hearts
        if(visibility[0])
            heart3.setVisibility(View.INVISIBLE);
        if(visibility[1])
            heart2.setVisibility(View.INVISIBLE);
        if(visibility[2])
            heart1.setVisibility(View.INVISIBLE);

        //Tools
        if(visibility[3])
            ihammer.setVisibility(View.VISIBLE);
        if(visibility[4])
            ilighter.setVisibility(View.VISIBLE);
        if(visibility[5])
        {
            linMessage.setVisibility(View.VISIBLE);
            linDecode.setVisibility(View.VISIBLE);
        }

        //Orbs
        if(visibility[6])
            orbG.setVisibility(View.VISIBLE);
        if(visibility[7])
            orbB.setVisibility(View.VISIBLE);
        if(visibility[8])
            orbR.setVisibility(View.VISIBLE);

        //Button for the decoder
        btnDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Decodes the input
                boolean decoded = db.insert(txtDecode.getText().toString());
                inputs = new ArrayList<>();
                outputs = new ArrayList<>();

                if(decoded)
                    Toast.makeText(getActivity(), "Decode Successful", Toast.LENGTH_SHORT).show();

                SQLiteDatabase db1 = db.getReadableDatabase();
                String select = "Select * from decode";
                Cursor myCur = db1.rawQuery(select, null);

                if(myCur != null)
                {
                    myCur.move(-1);
                    while(myCur.moveToNext())
                    {
                        inputs.add(myCur.getString(1));
                        outputs.add(myCur.getString(2));
                    }
                }

                scDecode.setAdapter(new DecodeAdapter(getContext(), inputs, outputs));
            }
        });
        return v;
    }

    //Called from the main activity
    public void changeVisible(int i)
    {
        //Determines what to make visible
        switch(i)
        {
            case -3:
                visibility[0] = true;
                break;
            case -2:
                visibility[1] = true;
                break;
            case -1:
                visibility[2] = true;
                break;

            case 0:
                visibility[3] = true;
                break;
            case 1:
                visibility[4] = true;
                break;
            case 2:
                visibility[5] = true;
                break;

            case 3:
                visibility[6] = true;
                break;
            case 4:
                visibility[7] = true;
                break;
            case 5:
                visibility[8] = true;
                break;
        }
    }

    //Called from main to set the progress bar
    public void setProgress(int prog)
    {
       this.prog += prog;
    }
}
