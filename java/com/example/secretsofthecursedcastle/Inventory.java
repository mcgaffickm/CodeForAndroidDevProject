package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

    MainActivity mainActivity;

    ImageView orbG, orbR, orbB, ihammer, ilighter, heart1, heart2, heart3;

    LinearLayout linDecode, linMessage;

    ProgressBar progress;
    DecodeDBUtility db;

    Button btnDecode;
    EditText txtDecode;
    ListView scDecode;

    List<String> inputs;
    List<String> outputs;

    public Inventory() {
    inputs = new ArrayList<> ();
    outputs = new ArrayList<>();
    }

    public void insertActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_inventory, container, false);
        orbR = v.findViewById(R.id.imgRed);
        orbB = v.findViewById(R.id.imgBlue);
        orbG = v.findViewById(R.id.imgGreen);
        heart1 = v.findViewById(R.id.imgHeart1);
        heart2 = v.findViewById(R.id.imgHeart3);
        heart3 = v.findViewById(R.id.imgHeart3);
        ihammer = v.findViewById(R.id.imgHammer);
        ilighter = v.findViewById(R.id.imgLighter);
        progress = v.findViewById(R.id.progBar);
        linDecode = v.findViewById(R.id.containDecoder);
        linMessage = v.findViewById(R.id.containMessages);
        btnDecode = v.findViewById(R.id.btnDecode);
        txtDecode = v.findViewById(R.id.txtDecode);
        scDecode = v.findViewById(R.id.scrPaper);
        db = new DecodeDBUtility(getActivity());

        orbR.setVisibility(View.INVISIBLE);
        orbG.setVisibility(View.INVISIBLE);
        orbR.setVisibility(View.INVISIBLE);
        heart1.setVisibility(View.VISIBLE);
        heart2.setVisibility(View.VISIBLE);
        heart3.setVisibility(View.VISIBLE);
        ihammer.setVisibility(View.INVISIBLE);
        ilighter.setVisibility(View.INVISIBLE);
        linMessage.setVisibility(View.INVISIBLE);
        linDecode.setVisibility(View.INVISIBLE);

        btnDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean decoded = db.insert(txtDecode.getText().toString());
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

    public void changeVisible(int i)
    {
        switch(i)
        {
            case -3:
                heart3.setVisibility(View.INVISIBLE);
                break;
            case -2:
                heart2.setVisibility(View.INVISIBLE);
                break;
            case -1:
                heart1.setVisibility(View.INVISIBLE);
                break;

            case 0:
                ihammer.setVisibility(View.VISIBLE);
                break;
            case 1:
                ilighter.setVisibility(View.VISIBLE);
                break;
            case 2:
                linMessage.setVisibility(View.VISIBLE);
                linDecode.setVisibility(View.VISIBLE);
                break;

            case 3:
                orbG.setVisibility(View.VISIBLE);
                break;
            case 4:
                orbB.setVisibility(View.VISIBLE);
                break;
            case 5:
                orbR.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setProgress(int prog)
    {
        progress.setProgress(prog);
    }
}
