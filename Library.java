package com.example.secretsofthecursedcastle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Library extends Fragment {

    //Declares stuff as every other class does
    ImageView books;
    EditText txtCode;
    boolean best;
    Button btn;
    float scale = 1f;
    ScaleGestureDetector scaleGesture;
    MainActivity activity;
    boolean finish;

    //Constructor
    public Library() {
        finish = false;
        best = false;
        // Required empty public constructor
    }

    //Inserts the activity
    public void insertActivity(MainActivity activity)
    {this.activity = activity; }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        //Sets a listener to zoom in on the picture
        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                scaleGesture.onTouchEvent(event);
                return true;
            }
        });

        //Finds the view for the books, buttons, text
        books = v.findViewById(R.id.imgBooks);
        btn = v.findViewById(R.id.btnLibraryComplete);
        txtCode = v.findViewById(R.id.txtType);

        //Gesture stuff
        scaleGesture = new ScaleGestureDetector(getContext(), new ScaleListener());

        //Message for best route
        if(activity.isBest())
            Toast.makeText(getContext(), "Enter NEWHISTORY to change your current destiny!", Toast.LENGTH_SHORT).show();

        //Kills the button if you already are done here
        if(finish)
            btn.setVisibility(View.INVISIBLE);

        //The button listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lets you continue on the best route if you enter the code
                if(activity.isBest())
                {
                    if(!best) {
                        if (txtCode.getText().toString().toUpperCase().equals("NEWHISTORY")) {
                            best = true;
                            activity.checkCount();
                            Toast.makeText(getContext(), "Setting Course for New History.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                //Otherwise code is island, gives you an orb for right and subtracts a life for wrong
                if(txtCode.getText().toString().toUpperCase().equals("ISLAND"))
                {
                    activity.addOrbs(3);
                    Toast.makeText(getContext(), "Correct: You have received the red orb.", Toast.LENGTH_SHORT).show();
                    finish = true;
                    btn.setVisibility(View.INVISIBLE);
                }
                else
                {
                    activity.subtractLives();
                    Toast.makeText(getContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    //The listener class to handle zooming
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= scaleGesture.getScaleFactor();
            scale = Math.max(0.1f,
                    Math.min(scale, 10.0f));
            books.setScaleX(scale);
            books.setScaleY(scale);

            return true;
        }
    }

}
