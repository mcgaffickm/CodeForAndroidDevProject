package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class History extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    SelectedOptions options;

    Intent gates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_history);

        tabLayout = findViewById(R.id.tabBar);
        viewPager = findViewById(R.id.vpHistory);

        HistoryTabAdapter tabAdapter = new HistoryTabAdapter(getSupportFragmentManager());
        tabAdapter.addFragments("How to Play", new IntroHowToPlay());
        tabAdapter.addFragments("Intro", new IntroFirst());
        tabAdapter.addFragments("Intro 2", new IntroSecond());
        tabAdapter.addFragments("The Gate", new IntroGate());

        gates = new Intent(History.this, MainActivity.class);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        options = (SelectedOptions) getIntent().getSerializableExtra("Options");
    }

    public void openGates1(View v)
    {
       gates.putExtra("Options", options);
        startActivity(gates);
    }
}
