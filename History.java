package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class History extends AppCompatActivity {

    //Declares the widgets and stuff
    TabLayout tabLayout;
    ViewPager viewPager;
    SelectedOptions options;

    Intent gates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_history);

        //Finds the views
        tabLayout = findViewById(R.id.tabBar);
        viewPager = findViewById(R.id.vpHistory);

        //Add the fragments to the tab adapter
        HistoryTabAdapter tabAdapter = new HistoryTabAdapter(getSupportFragmentManager());
        tabAdapter.addFragments("How to Play", new IntroHowToPlay());
        tabAdapter.addFragments("Intro", new IntroFirst());
        tabAdapter.addFragments("Intro 2", new IntroSecond());
        tabAdapter.addFragments("The Gate", new IntroGate());

        //Sets everything else up needed to traverse to main activity
        gates = new Intent(History.this, MainActivity.class);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        options = (SelectedOptions) getIntent().getSerializableExtra("Options");
    }

    //Called by a button to go to the main activity
    public void openGates1(View v)
    {
        Toast.makeText(this, "The journey begins!", Toast.LENGTH_SHORT).show();
        gates.putExtra("Options", options);
        startActivity(gates);
    }
}
