package com.example.secretsofthecursedcastle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Armory extends Fragment {

    ViewPager vp;
    MainActivity mainActivity;
    List<Tool> tools;

    public Armory() {
        tools = new ArrayList<>();
        tools.add(new Tool("Sword", "A blade from the ancient kingdom that has seen many battles.  It has an orange sheath set on the table nearby.", R.drawable.sword, false));
        tools.add(new Tool("Hammer", "A hammer that can smash through walls with ease.", R.drawable.hammer, true));
        tools.add(new Tool("Shield", "A sturdy shield that has defended knights from arrows and blades.  You see an O on the back.", R.drawable.shield, false));
        tools.add(new Tool("Axe", "A tool used mainly to chop firewood and cut down trees.", R.drawable.axe, false));
        tools.add(new Tool("Helm", "A helmet used by knights that can be prevent sword blows to the head.", R.drawable.helmet, false));
        tools.add(new Tool("Lighter", "Used to start campfires, but it can also be used to illuminate dark areas.", R.drawable.lighter, true));
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_armory, container, false);

        vp = v.findViewById(R.id.vpArmory);

        ArmoryPagerAdapater apa = new ArmoryPagerAdapater(tools, getContext());
        apa.insertActvity(mainActivity);


        vp.setAdapter(apa);

        return v;
    }

    public void insertActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }
}
