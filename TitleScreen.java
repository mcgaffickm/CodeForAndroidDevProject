package com.example.secretsofthecursedcastle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TitleScreen extends AppCompatActivity {

    //Intents and option variables
    Intent newGame;
    Intent option;
    SelectedOptions changedOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_title_screen);
        //Assigns the two intents
        newGame = new Intent(TitleScreen.this, History.class);
        option = new Intent(TitleScreen.this, Options.class);

    }

    //Starts a new game if clicked
    public void newGame(View v)
    {
        changedOptions = (SelectedOptions) getIntent().getSerializableExtra("Options");
        newGame.putExtra("Options", changedOptions);
        startActivity(newGame);
    }

    //Goes to options if clicked
    public void options(View v){
        changedOptions = (SelectedOptions) getIntent().getSerializableExtra("Options");
        option.putExtra("Options", changedOptions);
        startActivity(option);}
}
