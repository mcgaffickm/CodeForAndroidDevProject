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

    List<Tool> armTools;
    Context myContext;
    MainActivity mainActivity;


    public ArmoryPagerAdapater(List<Tool> armTools, Context myContext) {
        this.armTools = armTools;
        this.myContext = myContext;
    }

    public void insertActvity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return armTools.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==  o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.sample_armory_pager, container, false);
        ImageView img = v.findViewById(R.id.imgArmory);
        TextView name = v.findViewById(R.id.txtArmoryName);
        TextView description = v.findViewById(R.id.txtArmoryDescription);
        Button take = v.findViewById(R.id.btnArmoryTake);

        if(armTools.get(position).isTakeItem())
        {
            take.setVisibility(View.VISIBLE);
        }
        else
        {
            take.setVisibility(View.INVISIBLE);
        }

        name.setText(armTools.get(position).getName());

        final boolean hammer;

        if(armTools.get(position).getName().equals("Hammer"))
        {
            hammer = true;
        }
        else
        {
            hammer = false;
        }

        description.setText(armTools.get(position).getDescription());
        img.setImageResource(armTools.get(position).getImageID());

        final int iposition = position;

        take.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
             if(hammer)
             {
                 mainActivity.setAcquireTools(1);
                 Toast.makeText(myContext, "You have acquired the hammer!", Toast.LENGTH_SHORT).show();
                 armTools.get(iposition).setTakeItem(false);
                 v.setVisibility(View.INVISIBLE);
             }
             else
             {
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
