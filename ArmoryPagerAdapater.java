package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ArmoryPagerAdapater extends PagerAdapter {

    //Declares the variables needed
    List<Tool> armTools;
    Context myContext;
    MainActivity mainActivity;

    //Constructor
    public ArmoryPagerAdapater(List<Tool> armTools, Context myContext) {
        this.armTools = armTools;
        this.myContext = myContext;
    }

    //Brings main activity in
    public void insertActvity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    //Not used by me
    @Override
    public int getCount() {
        return armTools.size();
    }

    //Not used by me
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==  o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.sample_armory_pager, container, false);
        //Declares and finds widgets in the sample list
        ImageView img = v.findViewById(R.id.imgArmory);
        TextView name = v.findViewById(R.id.txtArmoryName);
        TextView description = v.findViewById(R.id.txtArmoryDescription);
        Button take = v.findViewById(R.id.btnArmoryTake);

        //Determines if the take button should be visible for the specific tool selected
        if(armTools.get(position).isTakeItem())
        {
            take.setVisibility(View.VISIBLE);
        }
        else
        {
            take.setVisibility(View.INVISIBLE);
        }

        //Changes the text for the tool name
        name.setText(armTools.get(position).getName());


        //Determines which takeable tool is currently open
        final boolean hammer;

        //If a hammer or lighter
        if(armTools.get(position).getName().equals("Hammer"))
            hammer = true;
        else
            hammer = false;

        //Sets the description and the images
        description.setText(armTools.get(position).getDescription());
        img.setImageResource(armTools.get(position).getImageID());

        //Set position for button
        final int iposition = position;

        //Allows you to collect the hammer and the lighter
        take.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
             if(hammer)
             {
                 //Adds the hammer if needed
                 mainActivity.setAcquireTools(1);
                 Toast.makeText(myContext, "You have acquired the hammer!", Toast.LENGTH_SHORT).show();
                 armTools.get(iposition).setTakeItem(false);
                 v.setVisibility(View.INVISIBLE);
             }
             else
             {
                 //Adds the lighter if needed, you cant get here if already have
                 mainActivity.setAcquireTools(2);
                 Toast.makeText(myContext, "You have acquired the lighter!", Toast.LENGTH_SHORT).show();
                 armTools.get(iposition).setTakeItem(false);
                 v.setVisibility(View.INVISIBLE);
             }
            }
        });

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
