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


/**
 * A simple {@link Fragment} subclass.
 */
public class Library extends Fragment {


    ImageView books;
    EditText txtCode;
    boolean best;
    Button btn;
    float scale = 1f;
    ScaleGestureDetector scaleGesture;

    MainActivity activity;

    public Library() {
        // Required empty public constructor
    }

    public void insertActivity(MainActivity activity)
    {
        this.activity = activity;
        best = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                scaleGesture.onTouchEvent(event);
                return true;
            }
        });

        books = v.findViewById(R.id.imgBooks);
        btn = v.findViewById(R.id.btnLibraryComplete);
        txtCode = v.findViewById(R.id.txtType);

        scaleGesture = new ScaleGestureDetector(getContext(), new ScaleListener());
        if(activity.isBest())
        {
            Toast.makeText(getContext(), "Enter NEWHISTORY to change your current destiny!", Toast.LENGTH_SHORT).show();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                if(txtCode.getText().toString().toUpperCase().equals("ISLAND"))
                {
                    activity.addOrbs(3);
                    Toast.makeText(getContext(), "Correct: You have received the red orb.", Toast.LENGTH_SHORT).show();
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
