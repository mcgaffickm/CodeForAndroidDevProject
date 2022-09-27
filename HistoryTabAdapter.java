package com.example.secretsofthecursedcastle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryTabAdapter extends FragmentPagerAdapter {

    //Declares the list
    List<String> titles;
    List <Fragment> fragments;

    //Stuff needed for tab adapter, plus list assignments
    public HistoryTabAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    //Add the stuff for the adapters
    public void addFragments(String title, Fragment f)
    {
        titles.add(title);
        fragments.add(f);
    }


    //I don't use this stuff, but it was required by classes
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
