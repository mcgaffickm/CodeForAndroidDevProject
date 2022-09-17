package com.example.secretsofthecursedcastle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryTabAdapter extends FragmentPagerAdapter {

    List<String> titles;
    List <Fragment> fragments;

    public HistoryTabAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    public void addFragments(String title, Fragment f)
    {
        titles.add(title);
        fragments.add(f);
    }


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
