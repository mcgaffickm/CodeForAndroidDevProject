package com.example.secretsofthecursedcastle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Hints extends Fragment {

    //Declares the list and pages
    List<String> titles;
    List<String> descriptions;
    List<Integer> images;
    ViewPager vp;

    public Hints() {
        //Assigns values to the three lists, could have used model class, but no real purpose for 3 variables
        titles = new ArrayList();
        descriptions = new ArrayList();
        images = new ArrayList();

        titles.add("Alchemy Lab");
        descriptions.add("No puzzle here, maybe drinking the potions will reveal something.");
        images.add(R.drawable.alchemy);

        titles.add("Armory");
        descriptions.add("No puzzle here, just a few things to solve other puzzles.  Try looking at every tool.");
        images.add(R.drawable.armory);

        titles.add("Dungeons");
        descriptions.add("These drawings are of weird symbols.  Maybe see what differentiates them from each other.  Something is up with that cracked wall too.");
        images.add(R.drawable.cells);

        titles.add("King's Bedroom");
        descriptions.add("These symbols seem familiar, have you seen them somewhere that would tell you which option to choose.");
        images.add(R.drawable.kingbedroom);

        titles.add("Library");
        descriptions.add("Try looking closer at the books, maybe a few words will stand out.  I wonder if there is some sort of device that you can use to see if any of these are the code.");
        images.add(R.drawable.library);

        titles.add("Tower");
        descriptions.add("Maybe there are some clues somewhere else that can be used to determine what switches to activate.");
        images.add(R.drawable.tower);

        titles.add("Main Door");
        descriptions.add("It seems like the door is sealed shut by a curse.  Maybe there is something that can be used to break the seal.");
        images.add(R.drawable.mainbackground);

        titles.add("Secret Location");
        descriptions.add("It’s too dark too see, there must be a way to brighten it up.");
        images.add(R.drawable.secretdark);

        titles.add("Final Trial");
        descriptions.add("The torches’ order and the symbol beneath them seem to be important.  Maybe there are clues elsewhere.");
        images.add(R.drawable.finaltrial);

        titles.add("Best Ending");
        descriptions.add(" This ending is difficult to find.  The starting point would be to look for the symbol of a star somewhere.  However, if you are reading this then the ending is unachievable during this playthrough.");
        images.add(R.drawable.best);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hints, container, false);
        //Finds the id for view pager
        vp = v.findViewById(R.id.vpHints);

        //Sets adapters and sends activity to adapter
        HintPagerAdapter hpa = new HintPagerAdapter(titles,descriptions,images, getContext());
        vp.setAdapter(hpa);

        return v;
    }
}
